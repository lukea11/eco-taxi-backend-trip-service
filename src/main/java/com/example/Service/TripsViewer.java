package com.example.Service;

import com.example.Interfaces.IViewTrips;
import io.grpc.stub.StreamObserver;
import proto.grpc.Trip;

import java.util.List;

public class TripsViewer implements IViewTrips {
    private Object Comparator;

    @Override
    public void view(Trip.GetBookingHistoryRequest request, StreamObserver<Trip.GetBookingHistoryResponse> responseObserver) {
        long page = request.getPage() ;
        long limit = request.getLimit(); //limit per page
        long id = request.getUserId(); // use to find in database
        List<Trip.BookingStatus> statuses = request.getBookingStatusesList();
        boolean order = request.getOrderAsc();

        // read from repository/ database
        // create list of trip objects

        // create Pagination Object
        // Just an example here, plug in the correct values
        Trip.Pagination pagination = Trip.Pagination.newBuilder()
                .setCurrentPage(1)   // Set current page
                .setPrevPage(0)      // Set previous page (or any other value)
                .setNextPage(2)      // Set next page
                .setTotalPage(10)    // Set total pages
                .build();



        Trip.GetBookingHistoryResponse.Builder response = Trip.GetBookingHistoryResponse.newBuilder();
        // set attributes of response
        //response.setPagination();
        //response.setResult();

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
}


