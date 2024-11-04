package com.example;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
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
        Server server = ServerBuilder.forPort(5003).addService(new TripService()).build();
        server.start();
        server.awaitTermination();
    }
}