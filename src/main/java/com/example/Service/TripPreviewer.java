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

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElement;
import com.google.maps.model.TravelMode;

public class TripPreviewer implements IPreviewTrip {
    private ICalculateFare fareCalculator;
    private final GeoApiContext geoApiContext;
    // private final com.example.Repository.TripRepository tripRepository;
    // private final com.example.Factory.ActionFactory actionFactory;  // Use the interface

    public TripPreviewer() {
        this.fareCalculator = new StandardFareCalculator();
        this.geoApiContext = new GeoApiContext.Builder() // Google API
                .apiKey(System.getenv("AIzaSyC7OkG0T-WWCXE63zCCllkH7DXL6MM8WV8"))
                .build();
    }

    @Override
    public void preview(Trip.SearchTripPreviewRequest request, StreamObserver<Trip.SearchTripPreviewResponse> responseObserver) {
        String pickup = request.getPickup(); // Should be street name
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
            // Communicate with API to find nearest locations
            String adjustedPickup;
            String adjustedDestination;
            LatLng pickupLocation = getCoordinates(pickup);
            LatLng destinationLocation = getCoordinates(destination);
            // Communicate with API to find
            //rainfall, traffic..

            JSONObject jsonObject = new JSONObject(jsonResponse.toString());
            JSONArray featuresArray = jsonObject.getJSONArray("features");
            JSONObject firstFeature = featuresArray.getJSONObject(0);
            JSONArray coordinates = firstFeature.getJSONObject("geometry").getJSONArray("coordinates");
            numOfAvailableTaxis = firstFeature.getJSONObject("properties").getInt("taxi_count");

            nearestTaxiCoordinates[0] = coordinates.getJSONArray(0).getDouble(0);
            nearestTaxiCoordinates[1] = coordinates.getJSONArray(0).getDouble(1);

            estimatedWaitingTime = calculateWaitingTime(numOfAvailableTaxis);
            distance = calculateDistanceBetweenLocations(pickupLocation, destinationLocation);
            fare = fareCalculator.calculate();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ApiException e) {
            throw new RuntimeException(e);
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

    // Finds coordinates based on the entered address
    private LatLng getCoordinates(String address) throws InterruptedException, ApiException, IOException {
        GeocodingResult[] results = GeocodingApi.geocode(geoApiContext, address).await();
        if (results.length > 0) {
            return results[0].geometry.location;
        }
        throw new IllegalArgumentException("Address not found: " + address);
    }

    // Calculates the distance between 2 sets of coordinates
    private double calculateDistanceBetweenLocations(LatLng pickup, LatLng destination) throws InterruptedException, ApiException, IOException {
        DistanceMatrix matrix = DistanceMatrixApi.newRequest(geoApiContext)
                .origins(pickup)
                .destinations(destination)
                .mode(TravelMode.DRIVING)
                .await();

        DistanceMatrixElement element = matrix.rows[0].elements[0];
        if (element.distance != null) {
            return element.distance.inMeters / 1000.0; // Convert to kilometers
        }
        throw new IllegalArgumentException("Could not calculate distance between locations.");
    }

    private int calculateWaitingTime(int taxiCount) {
        double multiplier = 2.5; // to change
        return (int) Math.max(60, 600 - taxiCount * multiplier);
    }

}