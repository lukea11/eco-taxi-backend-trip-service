package com.example.Interfaces;

import io.grpc.stub.StreamObserver;
import proto.grpc.Trip;

public interface IViewTrips {
    public abstract void view(Trip.GetBookingHistoryRequest request, StreamObserver<Trip.GetBookingHistoryResponse> responseObserver);
}


