package com.example.Service;

import com.example.Interfaces.IUpdateTrip;
import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import proto.grpc.Trip;

public class TripUpdater implements IUpdateTrip {
    @Override
    public void update(Trip.UpdateBookingRequest request, StreamObserver<Trip.UpdateBookingResponse> responseObserver) {
        long id= request.getId();
        String pickup = request.getPickup();
        String destination = request.getDestination();
        double distance = request.getDistance();
        double fare = request.getFare();
        String card_number = request.getCardNumber();
        Timestamp timestamp = request.getEstimatedArrivalDateTime();
        long estimatedWaitingTime = request.getEstimatedWaitingTime();
        Trip.BookingStatus bookingStatus = request.getBookingStatus();
        long userId = request.getUserId();

        // Logic
        // Database function to find trip object and change the booking status

        Trip.UpdateBookingResponse.Builder response = Trip.UpdateBookingResponse.newBuilder();
        // set attributes of response

        if (bookingStatus == Trip.BookingStatus.CANCELED){
            response.setResult("com.example.Factory.Trip Successfully Canceled!");
        } else if (bookingStatus == Trip.BookingStatus.COMPLETED) {
            response.setResult("com.example.Factory.Trip is now Completed!");
        }

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
}
