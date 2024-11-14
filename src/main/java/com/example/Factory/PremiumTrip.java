package com.example.Factory;

import com.example.Enums.TripStatus;

import java.sql.Timestamp;

public class PremiumTrip extends Trip{
    private final int passengers;
    private final int privacy;

    public PremiumTrip(long userId, TripStatus tripStatus, String pickupLocation, String destination, double distance, double fare,
                        String cardNumber, Timestamp estimatedArrivalDateTime, long estimatedWaitingTime, int passengers, int privacy) {
        super(userId, tripStatus, pickupLocation, destination, distance, fare, cardNumber, estimatedArrivalDateTime, estimatedWaitingTime);
        this.passengers = passengers;
        this.privacy = privacy;
    }

    public PremiumTrip(){
        this.passengers = 6;
        this.privacy = 5;
    }

    public int getPassengers() {
        return passengers;
    }

    public int getPrivacy() {
        return privacy;
    }
}
