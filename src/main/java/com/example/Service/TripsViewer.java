package com.example.Service;

import com.example.Enums.TripStatus;
import com.example.Interfaces.IViewTrips;
import com.example.Repository.ITripRepository;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.checkerframework.common.value.qual.EnsuresMinLenIf;
import proto.grpc.Trip;
import com.google.protobuf.Timestamp;

import java.util.ArrayList;
import java.util.List;

public class TripsViewer implements IViewTrips {
    private ITripRepository tripRepository;
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
        List<com.example.Factory.Trip> bookingHistory = tripRepository.findAllTrips((int) page, (int) limit, (int) id, statuses, order); // Fetch data from MySQL
        long totalPages = (long) tripRepository.getTotalPages((int) limit, (int) id, statuses);

        // For Pagination Object
        long nextPage = 0, previousPage = 0;
        if (page == 1){
            nextPage = -1;
        } else if (page == totalPages) {
            previousPage = -1;
        } else{
            nextPage = page-1;
            previousPage = page+1;
        }

        // create Pagination Object
        // Just an example here, plug in the correct values
        Trip.Pagination pagination = Trip.Pagination.newBuilder()
                .setCurrentPage(page)   // Set current page
                .setPrevPage(previousPage)      // Set previous page (or any other value)
                .setNextPage(nextPage)      // Set next page
                .setTotalPage(totalPages)    // Set total pages
                .build();

        if (bookingHistory.isEmpty()){
            responseObserver.onError(Status.NOT_FOUND.withDescription("No Trips Found").withCause(new TripNotFoundException("ensure correct filters provided in request or make a new Trip")).asException());
        }

        else{
            Trip.GetBookingHistoryResponse.Builder response = Trip.GetBookingHistoryResponse.newBuilder();
            // set attributes of response
            response.setPagination(pagination);

            for (com.example.Factory.Trip booking : bookingHistory) {
                Trip.TripBooking grpcBooking = Trip.TripBooking.newBuilder()
                        .setId(booking.getId())
                        .setPickup(booking.getPickup())
                        .setDestination(booking.getDestination())
                        .setDistance(booking.getDistance())
                        .setFare(booking.getFare())
                        .setCardNumber(booking.getCardNumber())
                        .setEstimatedArrivalDateTime(convertToProtobufTimestamp(booking.getEstimatedArrivalDateTime())) // convert Instant to protobuf Timestamp if necessary
                        .setEstimatedWaitingTime(booking.getEstimatedWaitingTime())
                        .setBookingStatus(Trip.BookingStatus.valueOf(booking.getTripStatus().name())) // Convert to gRPC enum
                        .setUserId(booking.getUserId())
                        .build();
                response.addResult(grpcBooking);
            }

            responseObserver.onNext(response.build());
            responseObserver.onCompleted();
        }
    }
    private com.google.protobuf.Timestamp convertToProtobufTimestamp(java.sql.Timestamp sqlTimestamp) {
        return com.google.protobuf.Timestamp.newBuilder().setSeconds(sqlTimestamp.getTime() / 1000).build();
    }

    private TripStatus mapBookingStatusToTripStatus(Trip.BookingStatus bookingStatus) {
        switch (bookingStatus) {
            case INCOMPLETED:
                return TripStatus.INCOMPLETED;
            case COMPLETED:
                return TripStatus.COMPLETED;
            case CANCELED:
                return TripStatus.CANCELED;
            default:
                throw new IllegalArgumentException("Unknown BookingStatus: " + bookingStatus);
        }
    }
}