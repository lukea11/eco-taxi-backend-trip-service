package com.example.Repository;

import com.example.Factory.Trip;

import java.util.ArrayList;

public interface ITripRepository {
	Trip findIncompletedTrip(int userId);
    boolean updateTripStatus(int tripId, proto.grpc.Trip.BookingStatus newBookingStatus);
    boolean insertNewTrip(Trip trip);
    ArrayList<Trip> findAllTrips(int page, int limit, int userId, ArrayList<proto.grpc.Trip.BookingStatus> statuses, boolean asc);
}
