package com.example.Factory;
import org.springframework.stereotype.Component;

@Component
public class TripCreator extends TripFactory {
    public TripCreator() {}

    @Override
    public Trip createTrip() {
        return new StandardTrip();
    }
}
