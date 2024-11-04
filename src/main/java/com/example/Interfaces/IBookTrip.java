package com.example.Interfaces;

import io.grpc.stub.StreamObserver;
import proto.grpc.Trip;

public interface IBookTrip {
    public void book(Trip.ConfirmBookingRequest request, StreamObserver<Trip.ConfirmBookingResponse> responseObserver);
}
