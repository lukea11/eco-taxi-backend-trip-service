package com.example.Service;

import com.example.Interfaces.*;
import io.grpc.stub.StreamObserver;
import proto.grpc.Trip;
import proto.grpc.TripServiceGrpc;
import org.springframework.stereotype.Service;

// Controller Class
@Service
public class TripService extends TripServiceGrpc.TripServiceImplBase {
    // facade design pattern
    private final IViewTrips tripViewer;
    private final IUpdateTrip tripUpdater;
    private final IPreviewTrip tripPreviewer;
    private final IBookTrip tripBooker;
    private final IGetIncompleteTrip incompleteTripGetter;

    public TripService() {
        this.tripViewer = new TripsViewer();
        this.tripUpdater = new TripUpdater();
        this.tripPreviewer = new TripPreviewer();
        this.tripBooker = new TripBooker();
        this.incompleteTripGetter = new IncompleteTripGetter();
    }

    @Override
    public void getBookingHistory(Trip.GetBookingHistoryRequest request, StreamObserver<Trip.GetBookingHistoryResponse> responseObserver) {
        this.tripViewer.view(request, responseObserver);
    }

    @Override
    public void confirmBooking(Trip.ConfirmBookingRequest request, StreamObserver<Trip.ConfirmBookingResponse> responseObserver) {
        this.tripBooker.book(request, responseObserver);
    }

    @Override
    public void updateBookingStatus(Trip.UpdateBookingRequest request, StreamObserver<Trip.UpdateBookingResponse> responseObserver) {
        this.tripUpdater.update(request, responseObserver);
    }

    @Override
    public void searchTripPreview(Trip.SearchTripPreviewRequest request, StreamObserver<Trip.SearchTripPreviewResponse> responseObserver) {
        this.tripPreviewer.preview(request, responseObserver);
    }

    @Override
    public void getIncompletedBooking(Trip.GetIncompletedBookingRequest request, StreamObserver<Trip.GetIncompletedBookingResponse> responseObserver) {
        this.incompleteTripGetter.get(request, responseObserver);
    }

}