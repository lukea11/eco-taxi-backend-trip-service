package com.example;

import com.example.Service.TripService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;


@SpringBootApplication
public class TripServiceApplication {

    public static void main(String[] args) throws InterruptedException, IOException {
        SpringApplication.run(TripServiceApplication.class, args);

        // Start the gRPC server
        Server server = ServerBuilder.forPort(5003)  // Listening on port 5003
                .addService(new TripService())  // Add your TripService implementation
                .build();
        server.start();  // Start the server
        System.out.println("Server started, waiting for termination...");
        server.awaitTermination();  // Wait for server termination
    }
}

