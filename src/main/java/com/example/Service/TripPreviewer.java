package com.example.Service;

import com.example.Interfaces.IPreviewTrip;
import io.grpc.stub.StreamObserver;
import proto.grpc.Trip;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElement;
import com.google.maps.model.TravelMode;
import java.io.IOException;

public class TripPreviewer implements IPreviewTrip {
    private final GeoApiContext geoApiContext;
    // private final com.example.Repository.TripRepository tripRepository;
    // private final com.example.Factory.ActionFactory actionFactory;  // Use the interface

    public TripPreviewer(){
        this.geoApiContext = new GeoApiContext.Builder() // Google API
                .apiKey(System.getenv("AIzaSyC7OkG0T-WWCXE63zCCllkH7DXL6MM8WV8"))
                .build();
    }

    @Override
    public void preview(Trip.SearchTripPreviewRequest request, StreamObserver<Trip.SearchTripPreviewResponse> responseObserver) {
        String pickup = request.getPickup(); // Should be street name
        String destination = request.getDestination();


        // Communicate with API to find nearest locations
        String adjustedPickup;
        String adjustedDestination;
        LatLng pickupLocation = getCoordinates(pickup);
        LatLng destinationLocation = getCoordinates(destination);
        // Communicate with API to find
        //rainfall, traffic..

        // Communicate with API to find

        /*
        double distance;
        double fare = calculateFare(distance);
        int num_of_available_taxis; // in user's 'region'
        int estimated_waiting_time = calculateWaitingTime(num_of_available_taxis);
        double[] nearest_taxi_coordinates;
         */

        // Use Factory to create new com.example.Factory.Trip Object

        // Add com.example.Factory.Trip into database as incomplete

        Trip.SearchTripPreviewResponse.Builder response = Trip.SearchTripPreviewResponse.newBuilder();

        /*
        response.setPickup(adjustedPickup);
        response.setDestination(adjustedDestination);
        response.setDistance(distance);
        response.setFare(fare);
        response.setEstimatedArrivalDateTime(estimated_arrival_date_time);
        response.setEstimatedWaitingTime(num_of_available_taxis);
        response.setNearestTaxiCoordinates(nearest_taxi_coordinates);

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();

         */

    }

    // Finds coordinates based on the entered address
    private LatLng getCoordinates(String address) throws InterruptedException, ApiException, IOException, IOException {
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

    // private methods for calculation

    /*
    private double calculateFare(double distance){
        double rate; // can make it dependent on time of day
        return rate*distance;
    }

    private int calculateWaitingTime(int no_of_available_taxis){
        double multiplier; // can be dependent on time of day
        return (int) (no_of_available_taxis * multiplier);
    }

    private int calculateTravelTime(double distance, Rainfall rainfall, Traffic traffic){
        return (int)
    }

     */
}
