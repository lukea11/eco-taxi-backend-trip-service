package com.example.Interfaces;

import io.grpc.stub.StreamObserver;
import proto.grpc.Trip;

public interface IPreviewTrip {
    public void preview(Trip.SearchTripPreviewRequest request, StreamObserver<Trip.SearchTripPreviewResponse> responseObserver);
}
