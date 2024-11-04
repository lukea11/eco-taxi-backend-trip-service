package com.example.Factory;

import com.example.Enums.TripStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "standard_trips")  // Defines the table for com.example.Factory.StandardTrip
public class StandardTrip extends Trip {

    // Constructor with all fields
    public StandardTrip(int tripId, int userId, TripStatus tripStatus, String pickupLocation, String destination, Date date, int tripRating) {
        super(tripId, userId, tripStatus, pickupLocation, destination, date, tripRating);
    }

    // Default constructor
    public StandardTrip() {}
}
