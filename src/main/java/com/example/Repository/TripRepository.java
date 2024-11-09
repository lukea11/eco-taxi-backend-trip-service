package com.example.Repository;

import com.example.Factory.Trip;

import java.util.ArrayList;

public class TripRepository implements ITripRepository{

    @Override
    public Trip findIncompletedTrip(int userId) {
        return null;
    }

    @Override
    public boolean updateTripStatus(int tripId, proto.grpc.Trip.BookingStatus newBookingStatus) {
        return false;
    }

    @Override
    public boolean insertNewTrip(Trip trip) {
        return false;
    }

    @Override
    public ArrayList<Trip> findAllTrips(int page, int limit, int userId, ArrayList<proto.grpc.Trip.BookingStatus> statuses, boolean asc) {
        return null;
    }
}
