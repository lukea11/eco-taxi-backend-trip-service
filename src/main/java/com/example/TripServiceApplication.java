package com.example;

import com.example.Service.TripService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

@SpringBootApplication
public class TripServiceApplication {

    public static void main(String[] args) throws InterruptedException, IOException {
        SpringApplication.run(TripServiceApplication.class, args);

        // Start the gRPC server
        Server server = ServerBuilder.forPort(5003).addService(new TripService()).build();
        System.out.println("gRPC Trip Service server started on port 5003");

        try {
            server.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        server.awaitTermination();
    }
}
