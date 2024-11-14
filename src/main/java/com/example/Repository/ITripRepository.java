package com.example.Repository;

import com.example.Factory.Trip;

import java.util.ArrayList;
import java.util.List;

public interface ITripRepository {
    Trip findIncompletedTrip(int userId);
    boolean updateTripStatus(int tripId, proto.grpc.Trip.BookingStatus newBookingStatus);
    boolean insertNewTrip(Trip trip);
    ArrayList<Trip> findAllTrips(int page, int limit, int userId, List<proto.grpc.Trip.BookingStatus> statuses, boolean asc);
    int getTotalPages(int limit, int id, List<proto.grpc.Trip.BookingStatus> statuses);
}