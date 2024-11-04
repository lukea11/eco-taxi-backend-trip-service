package com.example.Interfaces;

import io.grpc.stub.StreamObserver;
import proto.grpc.Trip;

public interface IUpdateTrip {
    public void update(Trip.UpdateBookingRequest request, StreamObserver<Trip.UpdateBookingResponse> responseObserver);
}
