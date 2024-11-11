package com.example.Service;

import com.example.Factory.Trip;
import com.example.Factory.TripCreator;
import com.example.Enums.TripStatus;
import com.example.Interfaces.IBookTrip;
import com.example.Repository.TripRepository;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proto.grpc.Trip.ConfirmBookingRequest;
import proto.grpc.Trip.ConfirmBookingResponse;
import proto.grpc.Trip.BookingStatus;
import com.google.protobuf.Timestamp;

@Service
public class TripBooker implements IBookTrip {

    @Autowired
    private TripRepository tripRepository;

    private TripCreator tripCreator = new TripCreator(); // Factory instance

    @Override
    public void book(ConfirmBookingRequest request, StreamObserver<ConfirmBookingResponse> responseObserver) {
        try {
            long userId = request.getUserId();
            String pickupLocation = request.getPickup();
            String destination = request.getDestination();
            double distance = request.getDistance();
            double fare = request.getFare();
            Timestamp estimatedArrivalDateTime = request.getEstimatedArrivalDateTime();
            long estimatedWaitingTime = request.getEstimatedWaitingTime();
            String cardNumber = request.getCardNumber();
            BookingStatus bookingStatus = request.getBookingStatus();

            TripStatus tripStatus = mapBookingStatusToTripStatus(bookingStatus);

            Trip newTrip = tripCreator.createTrip();

            newTrip.setUserId(userId);
            newTrip.setTripStatus(tripStatus);
            newTrip.setPickUpLocation(pickupLocation);
            newTrip.setDestination(destination);
            newTrip.setDistance(distance);
            newTrip.setFare(fare);
            newTrip.setEstimatedArrivalDateTime(convertToSqlTimestamp(estimatedArrivalDateTime));
            newTrip.setEstimatedWaitingTime(estimatedWaitingTime);
            newTrip.setCardNumber(cardNumber);

            boolean savedTrip = tripRepository.insertNewTrip(newTrip);
            String resultMessage = (savedTrip) ? "Trip Successfully Booked!" : "Trip booking failed. Please try again.";
            ConfirmBookingResponse response = ConfirmBookingResponse.newBuilder()
                    .setResult(resultMessage)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            ConfirmBookingResponse errorResponse = ConfirmBookingResponse.newBuilder()
                    .setResult("Trip booking failed. Please try again.")
                    .build();

            responseObserver.onNext(errorResponse);
            responseObserver.onCompleted();
        }
    }
    // Convert `protobuf.Timestamp` to `java.sql.Timestamp`
    private java.sql.Timestamp convertToSqlTimestamp(Timestamp protobufTimestamp) {
        return new java.sql.Timestamp(protobufTimestamp.getSeconds() * 1000);
    }

    private TripStatus mapBookingStatusToTripStatus(BookingStatus bookingStatus) {
        switch (bookingStatus) {
            case INCOMPLETED:
                return TripStatus.INCOMPLETED;
            case COMPLETED:
                return TripStatus.COMPLETED;
            case CANCELED:
                return TripStatus.CANCELED;
            default:
                throw new IllegalArgumentException("Unknown BookingStatus: " + bookingStatus);
        }
    }

}