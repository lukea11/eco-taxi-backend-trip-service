package com.example;

import com.example.Service.TripService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.net.InetSocketAddress;

@SpringBootApplication
public class TripServiceApplication {
    private Server server;

    private void start() throws IOException {
        server = ServerBuilder.forPort(5003)
                .addService(new TripService())
                .build()
                .start();
        System.out.println("TripService gRPC server started, listening on port 5003");

        // Add shutdown hook for cleanup
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("Shutting down gRPC server");
            TripServiceApplication.this.stop();
        }));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final TripServiceApplication server = new TripServiceApplication();
        server.start();
        server.blockUntilShutdown();
    }
}
