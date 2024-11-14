package com.example;

import com.example.Service.TripService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.grpc.Server;
import io.grpc.ServerBuilder;

@SpringBootApplication
public class TripServiceApplication {

    public static void main(String[] args) throws InterruptedException{
        SpringApplication.run(TripServiceApplication.class, args);

    }
}
