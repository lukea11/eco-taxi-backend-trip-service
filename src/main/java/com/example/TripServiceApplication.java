package com.example;

import com.example.Service.TripBooker;
import com.example.Service.TripPreviewer;
import com.example.Interfaces.IPreviewTrip;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import proto.grpc.Trip;
import io.grpc.stub.StreamObserver;

@SpringBootApplication
public class TripServiceApplication implements CommandLineRunner {

    private final TripPreviewer tripPreviewer;
    private final TripBooker tripBooker;

    public TripServiceApplication(TripPreviewer tripPreviewer, TripBooker tripBooker) {
        this.tripPreviewer = tripPreviewer;
        this.tripBooker = tripBooker;
    }

    public static void main(String[] args) {
        SpringApplication.run(TripServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Create a mock StreamObserver to capture the preview response
        StreamObserver<Trip.SearchTripPreviewResponse> responseObserver = new StreamObserver<Trip.SearchTripPreviewResponse>() {
            @Override
            public void onNext(Trip.SearchTripPreviewResponse value) {
                // Print the response to verify if everything is working
                System.out.println("Pickup: " + value.getPickup());
                System.out.println("Destination: " + value.getDestination());
                System.out.println("Distance: " + value.getDistance());
                System.out.println("Fare: " + value.getFare());
                System.out.println("Estimated Arrival Date Time: " + value.getEstimatedArrivalDateTime());
                System.out.println("Estimated Waiting Time: " + value.getEstimatedWaitingTime());
                System.out.println("Number of Available Taxis: " + value.getNumOfAvailableTaxis());
                System.out.println("Nearest Taxi Coordinates: " + value.getNearestTaxiCoordinatesList());

                // Now we trigger the booking process
                Trip.BookingStatus bookingStatus = Trip.BookingStatus.INCOMPLETED; // Status can be customized
                Trip.ConfirmBookingRequest bookingRequest = Trip.ConfirmBookingRequest.newBuilder()
                        .setUserId(123)  // Sample user ID, replace with actual data
                        .setPickup(value.getPickup())
                        .setDestination(value.getDestination())
                        .setDistance(value.getDistance())
                        .setFare(value.getFare())
                        .setEstimatedArrivalDateTime(value.getEstimatedArrivalDateTime())
                        .setEstimatedWaitingTime(value.getEstimatedWaitingTime())
                        .setCardNumber("1234-5678-9012-3456")  // Sample card number, replace with actual data
                        .setBookingStatus(bookingStatus)
                        .build();

                // Call the book method to confirm the trip booking
                tripBooker.book(bookingRequest, new StreamObserver<Trip.ConfirmBookingResponse>() {
                    @Override
                    public void onNext(Trip.ConfirmBookingResponse value) {
                        // Handle booking confirmation response
                        System.out.println("Booking status: " + value.getResult());
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("Booking completed.");
                    }
                });
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("Preview completed.");
            }
        };

        // Create a mock preview request
        Trip.SearchTripPreviewRequest previewRequest = Trip.SearchTripPreviewRequest.newBuilder()
                .setPickup("Singapore River")  // Test pickup location
                .setDestination("Sentosa Island")  // Test destination
                .build();

        // Call the preview method
        tripPreviewer.preview(previewRequest, responseObserver);
    }

}
