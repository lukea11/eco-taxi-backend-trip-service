package com.example.Factory;

public class TripCreator extends TripFactory {
    public TripCreator() {}

    @Override
    public Trip createTrip() {
        return new StandardTrip();
    }
}
