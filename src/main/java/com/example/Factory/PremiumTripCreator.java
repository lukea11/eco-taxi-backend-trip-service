package com.example.Factory;

public class PremiumTripCreator extends TripCreator {
    public PremiumTripCreator() {}
    @Override
    public Trip createTrip() {
        return new PremiumTrip();
    }
}
