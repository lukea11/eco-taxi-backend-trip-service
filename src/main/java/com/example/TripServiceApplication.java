package com.example;

import com.example.Service.TripService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.net.InetSocketAddress;

@SpringBootApplication
public class TripServiceApplication {


    public static void main(String[] args) throws InterruptedException{
        SpringApplication.run(TripServiceApplication.class, args);

        // Start the gRPC server
        Server server = ServerBuilder.forPort(new InetSocketAddress("127.0.0.1", 5003).getPort())
                .addService(new TripService())
                .build();

        try {
            server.start();
            System.out.println("gRPC Trip Service server started on port" + server.getPort());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        server.awaitTermination();


    }

}
