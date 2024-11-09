package com.example.Service;

import com.example.Interfaces.IBookTrip;
import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import proto.grpc.Trip;

public class TripBooker implements IBookTrip {
    @Override
    public void book(Trip.ConfirmBookingRequest request, StreamObserver<Trip.ConfirmBookingResponse> responseObserver) {
        String pickup = request.getPickup();
        String destination = request.getDestination();
        double distance = request.getDistance();
        double fare = request.getFare();
        String card_number = request.getCardNumber();
        Timestamp timestamp = request.getEstimatedArrivalDateTime();
        long estimatedWaitingTime = request.getEstimatedWaitingTime();
        Trip.BookingStatus bookingStatus = request.getBookingStatus();
        long userId = request.getUserId();

        Trip.ConfirmBookingResponse.Builder response = Trip.ConfirmBookingResponse.newBuilder();
        // set attributes of response
        response.setResult("Trip Successfully Booked!");

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }


}


