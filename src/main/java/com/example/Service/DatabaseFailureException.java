package com.example.Service;

public class DatabaseFailureException extends RuntimeException{
    public DatabaseFailureException(String message){
        super(message);
    }
}
