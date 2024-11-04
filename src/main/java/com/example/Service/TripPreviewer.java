package com.example.Service;

import com.example.Interfaces.IPreviewTrip;
import io.grpc.stub.StreamObserver;
import proto.grpc.Trip;

public class TripPreviewer implements IPreviewTrip {
    // private final com.example.Repository.TripRepository tripRepository;
    // private final com.example.Factory.ActionFactory actionFactory;  // Use the interface

    public TripPreviewer(){

    }


    @Override
    public void preview(Trip.SearchTripPreviewRequest request, StreamObserver<Trip.SearchTripPreviewResponse> responseObserver) {
        String pickup = request.getPickup();
        String destination = request.getDestination();


        // Communicate with API to find nearest locations
        String adjustedPickup;
        String adjustedDestination;

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
