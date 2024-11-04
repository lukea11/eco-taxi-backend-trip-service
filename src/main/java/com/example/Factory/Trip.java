package com.example.Factory;

import com.example.Enums.TripStatus;
import jakarta.persistence.*;
import com.google.protobuf.Timestamp;
import java.time.Instant;

import java.util.Date;

@Entity
@Table(name = "trips")
@Inheritance(strategy = InheritanceType.JOINED)  // This ensures subclasses will have a proper relationship in the database
public abstract class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increment for the tripId field
    @Column(name = "trip_id")
    private int tripId;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Enumerated(EnumType.STRING)  // Maps the Enum to a string column in the database
    @Column(name = "trip_status", nullable = false)
    private TripStatus tripStatus;

    @Column(name = "pickup_location", nullable = false)
    private String pickupLocation;

    @Column(name = "destination", nullable = false)
    private String destination;

    // @Temporal(TemporalType.TIMESTAMP)  // To store date and time
    @Column(name = "date", nullable = false)
    private Timestamp date;

    @Column(name = "trip_rating")
    private int tripRating;

    public Trip(int tripId, int userId, TripStatus tripStatus, String pickupLocation, String destination, Timestamp date) {
        this.tripId = tripId;
        this.userId = userId;
        this.tripStatus = tripStatus;
        this.pickupLocation = pickupLocation;
        this.destination = destination;
        this.date = date;
    }

    public Trip() {
        this.userId = 0;  
        this.tripStatus = TripStatus.INCOMPLETED;
        this.pickupLocation = "";  
        this.destination = "";  
        this.date = Timestamp.newBuilder()
                .setSeconds(Instant.now().getEpochSecond())
                .setNanos(Instant.now().getNano())
                .build();
    }

    public Trip(int tripId, int userId, TripStatus tripStatus, String pickupLocation, String destination, Date date, int tripRating) {
    }


    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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

    // private TimeStamp Builder
}
