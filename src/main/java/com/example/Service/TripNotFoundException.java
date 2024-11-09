package com.example.Service;

public class TripNotFoundException extends RuntimeException{
    public TripNotFoundException(String message){
        super(message);
    }
}
