package com.example.Service;

import com.example.Interfaces.IPreviewTrip;
import io.grpc.stub.StreamObserver;
import proto.grpc.Trip;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;
import com.google.protobuf.Timestamp;

public class TripPreviewer implements IPreviewTrip {

    public TripPreviewer() {
    }

    @Override
    public void preview(Trip.SearchTripPreviewRequest request, StreamObserver<Trip.SearchTripPreviewResponse> responseObserver) {
        String pickup = request.getPickup();
        String destination = request.getDestination();

        double fare = 0.0;
        Timestamp estimated_arrival_date_time = Timestamp.getDefaultInstance();
        long estimatedWaitingTime = 0;
        double distance = 0.0;
        double[] nearestTaxiCoordinates = new double[2];
        int numOfAvailableTaxis = 0;

        try {
            String apiURL = "https://api.data.gov.sg/v1/transport/taxi-availability";
            URL url = new URL(apiURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            Scanner scanner = new Scanner(url.openStream());
            StringBuilder jsonResponse = new StringBuilder();
            while (scanner.hasNext()) {
                jsonResponse.append(scanner.nextLine());
            }
            scanner.close();

            JSONObject jsonObject = new JSONObject(jsonResponse.toString());
            JSONArray featuresArray = jsonObject.getJSONArray("features");
            JSONObject firstFeature = featuresArray.getJSONObject(0);
            JSONArray coordinates = firstFeature.getJSONObject("geometry").getJSONArray("coordinates");
            numOfAvailableTaxis = firstFeature.getJSONObject("properties").getInt("taxi_count");

            nearestTaxiCoordinates[0] = coordinates.getJSONArray(0).getDouble(0);
            nearestTaxiCoordinates[1] = coordinates.getJSONArray(0).getDouble(1);

            estimatedWaitingTime = calculateWaitingTime(numOfAvailableTaxis);
            distance = calculateDistance(pickup, destination);
            fare = calculateFare(distance);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Trip.SearchTripPreviewResponse.Builder response = Trip.SearchTripPreviewResponse.newBuilder();

        response.setPickup(pickup);
        response.setDestination(destination);
        response.setDistance(distance);
        response.setFare(fare);

        Timestamp estimatedArrivalDateTime = Timestamp.newBuilder()
                .setSeconds(System.currentTimeMillis() / 1000 + estimatedWaitingTime)
                .build();
        response.setEstimatedArrivalDateTime(estimatedArrivalDateTime);

        response.setEstimatedWaitingTime(estimatedWaitingTime);
        response.setNumOfAvailableTaxis(numOfAvailableTaxis);
        response.addNearestTaxiCoordinates(nearestTaxiCoordinates[0]);
        response.addNearestTaxiCoordinates(nearestTaxiCoordinates[1]);

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    private double calculateFare(double distance) {
        double rate = 1.2; // to change based on google api
        return rate * distance;
    }

    private int calculateWaitingTime(int taxiCount) {
        double multiplier = 2.5; // to change
        return (int) Math.max(60, 600 - taxiCount * multiplier);
    }

    private double calculateDistance(String pickup, String destination) {
        return 10.5; // Placeholder value in kilometers
    }
}
