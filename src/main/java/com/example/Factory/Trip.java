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

    @Column(name = "pickup_location", nullable = false)
    private String pickupLocation;

    @Column(name = "destination", nullable = false)
    private String destination;

    @Column(name = "distance")
    private double distance;

    @Column(name = "fare")
    private double fare;

    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "estimated_arrival_date_time")
    private Timestamp estimatedArrivalDateTime;

    @Column(name = "estimated_waiting_time")
    private long estimatedWaitingTime;

    @Enumerated(EnumType.STRING)  // Maps the Enum to a string column in the database
    @Column(name = "trip_status", nullable = false)
    private TripStatus tripStatus;

    @Column(name = "user_id", nullable = false)
    private long userId;

    public Trip(long userId, TripStatus tripStatus, String pickupLocation, String destination, double distance, double fare,
                String cardNumber, Timestamp estimatedArrivalDateTime, long estimatedWaitingTime) {
        this.userId = userId;
        this.tripStatus = tripStatus;
        this.pickupLocation = pickupLocation;
        this.destination = destination;
        this.distance = distance;
        this.fare = fare;
        this.cardNumber = cardNumber;
        this.estimatedArrivalDateTime = estimatedArrivalDateTime;
        this.estimatedWaitingTime = estimatedWaitingTime;
    }

    public Trip() {
        this.userId = 0;
        this.tripStatus = TripStatus.INCOMPLETED;
        this.pickupLocation = "";
        this.destination = "";
        this.distance = 0;
        this.fare = 0;
        this.cardNumber = "";
        this.estimatedArrivalDateTime = Timestamp.newBuilder()
                .setSeconds(Instant.now().plusSeconds(300).getEpochSecond())
                .setNanos(Instant.now().getNano())
                .build();
        this.estimatedWaitingTime = 0;
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

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Timestamp getEstimatedArrivalDateTime() {
        return estimatedArrivalDateTime;
    }

    public void setEstimatedArrivalDateTime(Timestamp estimatedArrivalDateTime) {
        this.estimatedArrivalDateTime = estimatedArrivalDateTime;
    }

    public long getEstimatedWaitingTime() {
        return estimatedWaitingTime;
    }

    public void setEstimatedWaitingTime(long estimatedWaitingTime) {
        this.estimatedWaitingTime = estimatedWaitingTime;
    }

    public long getId() {
        return this.tripId;
    }

    public String getPickup() {
        return this.pickupLocation;
    }
}