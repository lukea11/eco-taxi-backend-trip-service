package com.example.Service;

import com.example.Interfaces.IViewTrips;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.checkerframework.common.value.qual.EnsuresMinLenIf;
import org.springframework.stereotype.Service;
import proto.grpc.Trip;

import java.util.ArrayList;
import java.util.List;

@Service
public class TripsViewer implements IViewTrips {
    private Object Comparator;

    @Override
    public void view(Trip.GetBookingHistoryRequest request, StreamObserver<Trip.GetBookingHistoryResponse> responseObserver) {
        long page = request.getPage();
        long limit = request.getLimit(); //limit per page
        long id = request.getUserId(); // use to find in database
        List<Trip.BookingStatus> statuses = request.getBookingStatusesList();
        boolean order = request.getOrderAsc();

        // read from database
        // create list of trip objects
        // store in bookingHistory as TripBooking objects
        List<Trip.TripBooking> bookingHistory = new ArrayList<>(); // Fetch data from MySQL

        // create Pagination Object
        // Just an example here, plug in the correct values
        Trip.Pagination pagination = Trip.Pagination.newBuilder()
                .setCurrentPage(1)   // Set current page
                .setPrevPage(0)      // Set previous page (or any other value)
                .setNextPage(2)      // Set next page
                .setTotalPage(10)    // Set total pages
                .build();

        if (bookingHistory.size() == 0){
            responseObserver.onError(Status.NOT_FOUND.withDescription("No Trips Found").withCause(new TripNotFoundException("ensure correct filters provided in request or make a new Trip")).asException());
        }

        else{
            Trip.GetBookingHistoryResponse.Builder response = Trip.GetBookingHistoryResponse.newBuilder();
            // set attributes of response
            response.setPagination(pagination);

            for (Trip.TripBooking booking : bookingHistory) {
                Trip.TripBooking grpcBooking = Trip.TripBooking.newBuilder()
                        .setId(booking.getId())
                        .setPickup(booking.getPickup())
                        .setDestination(booking.getDestination())
                        .setDistance(booking.getDistance())
                        .setFare(booking.getFare())
                        .setCardNumber(booking.getCardNumber())
                        .setEstimatedArrivalDateTime(booking.getEstimatedArrivalDateTime()) // convert Instant to protobuf Timestamp if necessary
                        .setEstimatedWaitingTime(booking.getEstimatedWaitingTime())
                        .setBookingStatus(Trip.BookingStatus.valueOf(booking.getBookingStatus().name())) // Convert to gRPC enum
                        .setUserId(booking.getUserId())
                        .build();
                response.addResult(grpcBooking);
            }

            responseObserver.onNext(response.build());
            responseObserver.onCompleted();
        }
    }
}


