package com.example.Factory;

import com.example.Enums.TripStatus;
import jakarta.persistence.*;
import com.google.protobuf.Timestamp;
import java.time.Instant;


@Entity
@Table(name = "trips")
@Inheritance(strategy = InheritanceType.JOINED)  // This ensures subclasses will have a proper relationship in the database
public abstract class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increment for the tripId field
    @Column(name = "trip_id")
    private int tripId;

    @Column(name = "user_id", nullable = false)
    private long userId; //changed to long

    @Enumerated(EnumType.STRING)  // Maps the Enum to a string column in the database
    @Column(name = "trip_status", nullable = false)
    private TripStatus tripStatus;

    @Column(name = "pickup_location", nullable = false)
    private String pickupLocation;

    @Column(name = "destination", nullable = false)
    private String destination;

    @Column(name = "distance")
    private double distance;

    @Column(name = "fare")
    private double fare;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Timestamp date;

    @Column(name = "trip_rating")
    private int tripRating;

    public Trip(long userId, TripStatus tripStatus, String pickupLocation, String destination, double distance, double fare, Timestamp date, int tripRating) {
        this.userId = userId;
        this.tripStatus = tripStatus;
        this.pickupLocation = pickupLocation;
        this.destination = destination;
        this.distance = distance;
        this.fare = fare;
        this.date = date;
        this.tripRating = tripRating;
    }

    public Trip() {
        this.userId = 0;
        this.tripStatus = TripStatus.INCOMPLETED;
        this.pickupLocation = "";
        this.destination = "";
        this.distance = 0;
        this.fare = 0;
        this.date = Timestamp.newBuilder()
                .setSeconds(Instant.now().getEpochSecond())
                .setNanos(Instant.now().getNano())
                .build();
        this.tripRating = 0;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public TripStatus getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(TripStatus tripStatus) {
        this.tripStatus = tripStatus;
    }

    public String getPickUpLocation() {
        return pickupLocation;
    }

    public void setPickUpLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getTripRating() {
        return tripRating;
    }

    public void setTripRating(int tripRating) {
        this.tripRating = tripRating;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

}
