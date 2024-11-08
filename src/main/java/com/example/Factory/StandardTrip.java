package com.example.Factory;

import com.example.Enums.TripStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import com.google.protobuf.Timestamp;

@Entity
@Table(name = "standard_trips")  // Defines the table for StandardTrip
public class StandardTrip extends Trip {

    public StandardTrip(long userId, TripStatus tripStatus, String pickupLocation, String destination, double distance, double fare, Timestamp date, int tripRating) {
        super(userId, tripStatus, pickupLocation, destination, distance, fare, date, tripRating);
    }

    public StandardTrip() {
        super();
    }
}
