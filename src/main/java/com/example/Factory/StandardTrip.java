package com.example.Factory;

import com.example.Enums.TripStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "standard_trips")  // Defines the table for StandardTrip
public class StandardTrip extends Trip {

    public StandardTrip(long userId, TripStatus tripStatus, String pickupLocation, String destination, double distance, double fare,
                        String cardNumber, Timestamp estimatedArrivalDateTime, long estimatedWaitingTime) {
        super(userId, tripStatus, pickupLocation, destination, distance, fare, cardNumber, estimatedArrivalDateTime, estimatedWaitingTime);
    }

    public StandardTrip() {
        super();
    }

}
