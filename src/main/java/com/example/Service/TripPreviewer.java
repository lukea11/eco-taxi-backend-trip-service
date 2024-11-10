package com.example.Service;

import com.example.Interfaces.IPreviewTrip;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;
import proto.grpc.Trip;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;
import com.google.protobuf.Timestamp;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElement;
import com.google.maps.model.TravelMode;

@Service
public class TripPreviewer implements IPreviewTrip {
    private final GeoApiContext geoApiContext;
    private final StandardFareCalculator fareCalculator;

    public TripPreviewer() {
        this.geoApiContext = new GeoApiContext.Builder() // Google API
                .apiKey(System.getenv("AIzaSyC7OkG0T-WWCXE63zCCllkH7DXL6MM8WV8"))
                .build();
        this.fareCalculator = new StandardFareCalculator();
    }

    @Override
    public void preview(Trip.SearchTripPreviewRequest request, StreamObserver<Trip.SearchTripPreviewResponse> responseObserver) {
        String pickup = request.getPickup(); // Should be street name
        String destination = request.getDestination();

        double fare = 0.0;
        Timestamp estimatedArrivalDateTime = Timestamp.getDefaultInstance();
        long estimatedWaitingTime = 0;
        double distance = 0.0;
        double[] nearestTaxiCoordinates = new double[2];
        int numOfAvailableTaxis = 0;

        try {
            LatLng pickupLocation = getCoordinates(pickup);
            LatLng destinationLocation = getCoordinates(destination);

            // Calculate distance using Google Maps Distance Matrix API
            distance = calculateDistanceBetweenLocations(pickupLocation, destinationLocation);
            fare = fareCalculator.calculate(distance);

            ZonedDateTime singaporeTime = ZonedDateTime.now(ZoneId.of("Asia/Singapore"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            String dateTime = singaporeTime.format(formatter);

            String apiURL = "https://api.data.gov.sg/v1/transport/taxi-availability?date_time=" + dateTime;
            URL url = new URL(apiURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Check response code
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 200 OK
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder jsonResponse = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    jsonResponse.append(inputLine);
                }
                in.close();

                JSONObject jsonObject = new JSONObject(jsonResponse.toString());

                if (jsonObject.has("features") && !jsonObject.getJSONArray("features").isEmpty()) {
                    JSONArray featuresArray = jsonObject.getJSONArray("features");
                    JSONObject firstFeature = featuresArray.getJSONObject(0);
                    JSONArray coordinates = firstFeature.getJSONObject("geometry").getJSONArray("coordinates");

                    // Finding the nearest taxi coordinates
                    LatLng nearestTaxi = null;
                    double minDistance = Double.MAX_VALUE;

                    for (int i = 0; i < coordinates.length(); i++) {
                        JSONArray coord = coordinates.getJSONArray(i);
                        double lng = coord.getDouble(0);
                        double lat = coord.getDouble(1);
                        LatLng taxiLocation = new LatLng(lat, lng);

                        // Calculate distance to the pickup location
                        double dist = calculateDistanceBetweenLocations(pickupLocation, taxiLocation);
                        if (dist < 2.0){
                            numOfAvailableTaxis++;
                        }
                        if (dist < minDistance) {
                            minDistance = dist;
                            nearestTaxi = taxiLocation;
                        }
                    }

                    if (nearestTaxi != null) {
                        nearestTaxiCoordinates[0] = nearestTaxi.lat;
                        nearestTaxiCoordinates[1] = nearestTaxi.lng;
                    }

                    estimatedWaitingTime = calculateWaitingTime(numOfAvailableTaxis);
                } else {
                    System.out.println("No taxi data available.");
                    numOfAvailableTaxis = 0;
                    estimatedWaitingTime = 600;
                }

            } else { // non-ok response
                System.out.println("Error fetching taxi data: " + responseCode);
                numOfAvailableTaxis = 0;
                estimatedWaitingTime = 600;
            }


        } catch (IOException | InterruptedException | ApiException e) {
            e.printStackTrace();
        }

        Trip.SearchTripPreviewResponse.Builder response = Trip.SearchTripPreviewResponse.newBuilder();
        response.setPickup(pickup);
        response.setDestination(destination);
        response.setDistance(distance);
        response.setFare(fare);

        estimatedArrivalDateTime = Timestamp.newBuilder()
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
        int baseWaitingTime = 600; // 10 minutes (in seconds)
        double multiplier = 1.0; // Base multiplier

        if (taxiCount <= 0) {
            multiplier = 5.0; // No taxis available, set a long waiting time
        } else if (taxiCount <= 5) {
            multiplier = 3.0; // Fewer taxis, longer wait
        } else if (taxiCount <= 10) {
            multiplier = 1.5; // Moderate wait
        } else {
            multiplier = 1.0; // More taxis, shorter wait
        }

        // Calculate the estimated waiting time
        int waitingTime = (int) (baseWaitingTime / multiplier);
        return waitingTime;
    }

}
