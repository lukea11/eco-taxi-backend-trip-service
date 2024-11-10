package com.example.Service;

import com.example.Interfaces.IGetIncompleteTrip;
import com.example.Repository.ITripRepository;
import com.example.Repository.TripRepository;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;
import proto.grpc.Trip;

@Service
public class IncompleteTripGetter implements IGetIncompleteTrip {
    private ITripRepository tripRepository;

    @Override
    public void get(Trip.GetIncompletedBookingRequest request, StreamObserver<Trip.GetIncompletedBookingResponse> responseObserver) {
        long userId = request.getUserId();
        Trip.BookingStatus bookingStatus = request.getBookingStatus();

        // Find from database the latest trip object
        com.example.Factory.Trip booking = this.tripRepository.findIncompletedTrip((int) userId);

        if (booking == null){
            responseObserver.onError(Status.NOT_FOUND.withDescription("No Incomplete Trips Found").withCause(new TripNotFoundException("ensure you book a trip first!")).asException());
        }

        else {
            Trip.GetIncompletedBookingResponse.Builder response = Trip.GetIncompletedBookingResponse.newBuilder();
            Trip.TripBooking grpcBooking = Trip.TripBooking.newBuilder()
                    .setId(booking.getId())
                    .setPickup(booking.getPickup())
                    .setDestination(booking.getDestination())
                    .setDistance(booking.getDistance())
                    .setFare(booking.getFare())
                    .setCardNumber(booking.getCardNumber())
                    .setEstimatedArrivalDateTime(booking.getEstimatedArrivalDateTime()) // convert Instant to protobuf Timestamp if necessary
                    .setEstimatedWaitingTime(booking.getEstimatedWaitingTime())
                    .setBookingStatus(Trip.BookingStatus.valueOf(booking.getTripStatus().name())) // Convert to gRPC enum
                    .setUserId(booking.getUserId())
                    .build();

            response.setTripBooking(grpcBooking);
            responseObserver.onNext(response.build());
            responseObserver.onCompleted();
        }
    }
}
