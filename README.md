# EcoTaxi Backend - Trip Service

EcoTaxi Backend - Trip Service is responsible for handling booking-related requests received from the API Gateway. It processes these requests using [Spring Boot](https://spring.io/projects/spring-framework), and [MySQL](https://dev.mysql.com/doc/) to store booking information.

## Git Repositories

This project is part of the EcoTaxi ecosystem, which includes multiple repositories for the frontend, backend services, and API gateway:

- **Frontend**: [EcoTaxi Frontend](https://github.com/haiyen11231/eco-taxi-frontend.git)
- **API Gateway**: [EcoTaxi API Gateway](https://github.com/haiyen11231/eco-taxi-api-gateway.git)
- **User Service**: [EcoTaxi User Service](https://github.com/haiyen11231/eco-taxi-backend-user-service.git)
- **Payment Service**: [EcoTaxi Payment Service](https://github.com/AWYS7/eco-taxi-payment-service.git)
- **Trip Service**: [EcoTaxi Trip Service](https://github.com/lukea11/eco-taxi-backend-trip-service.git)

## Directory Structure

```plaintext
eco-taxi-backend-trip-service/
│
├── .idea/
│
├── src/
│   └── main/
│       ├── java/
│       │   ├── com/example/
│       │   │   ├── Enums/
│       │   │   │   └── TripStatus.java
│       │   │   │
│       │   │   ├── Factory/
│       │   │   ├── Interfaces/
│       │   │   ├── Repository/
│       │   │   │   │── ITripRepository.java
│       │   │   │   └── TripRepository.java
│       │   │   │
│       │   │   ├── Service/
│       │   │   └── TripServiceApplication.java
│       │   │
│       │   └── proto/grpc/
│       │       ├── Trip.java
│       │       └── TripServiceGrpc.java
│       │
│       └── resources/
│           ├── application.properties
│           └── trip.proto
│
├── .gitignore
├── app.env
├── Dockerfile
├── pom.xml
└── README.md
```

## Prerequisites

Before you begin, ensure that you have the following installed:

- **Java**
- **gRPC Tools** (Protocol Buffers and gRPC Go)
- **MySQL**
- **Docker** (optional, for containerization)

## Installation

Clone the repository:

```bash
git clone https://github.com/lukea11/eco-taxi-backend-trip-service.git
cd eco-taxi-backend-trip-service
```
