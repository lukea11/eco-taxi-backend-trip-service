package com.example.Service;

import com.example.Interfaces.IGetIncompleteTrip;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;
import proto.grpc.Trip;

@Service
public class IncompleteTripGetter implements IGetIncompleteTrip {
    @Override
    public void get(Trip.GetIncompletedBookingRequest request, StreamObserver<Trip.GetIncompletedBookingResponse> responseObserver) {
        long userId = request.getUserId();
        Trip.BookingStatus bookingStatus = request.getBookingStatus();

        // Find from database the latest trip object

        Trip.GetIncompletedBookingResponse.Builder response = Trip.GetIncompletedBookingResponse.newBuilder();
        //response.setTripBooking();
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
}
