package com.example.Interfaces;

import io.grpc.stub.StreamObserver;
import proto.grpc.Trip;

public interface IGetIncompleteTrip {
    public void get(Trip.GetIncompletedBookingRequest request, StreamObserver<Trip.GetIncompletedBookingResponse> responseObserver);
}
