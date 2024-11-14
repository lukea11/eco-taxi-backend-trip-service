package com.example.Service;

import java.time.LocalTime;

public class PremiumFareCalculator implements ICalculateFare {

    @Override
    public double calculate(double distance) {
        double rate;
        int currentHour = LocalTime.now().getHour();
        boolean isPeakHour = (currentHour >= 6 && currentHour <= 10) || (currentHour >= 17 && currentHour <= 20);
        if (isPeakHour) {
            rate = 7.50; // Higher rate for peak hours
        } else {
            rate = 4.50; // Lower rate for off-peak hours
        }
        return rate * distance;
    }
}
