package com.example.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.Enums.TripStatus;
import com.example.Factory.StandardTrip;
import com.example.Factory.Trip;
import com.google.protobuf.InvalidProtocolBufferException;
import org.springframework.stereotype.Repository;
import proto.grpc.Trip.BookingStatus;
import java.sql.Timestamp;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Value;

@Repository
public class TripRepository implements ITripRepository{
    // Load environment variables from the .env file
    Dotenv dotenv = Dotenv.load();

    private String host = dotenv.get("MYSQL_HOST");
    private String port = dotenv.get("MYSQL_PORT");
    private String user = dotenv.get("MYSQL_USER");
    private String password = dotenv.get("MYSQL_PASSWORD");
    private String database = dotenv.get("MYSQL_DATABASE");

    private static Connection connection;

    // Constructor to establish a database connection when the repository is created
    public TripRepository() {
        connectToDatabase();
    }

    // Method to establish a database connection
    public void connectToDatabase() {
        try {
            String jdbcUrl = String.format("jdbc:mysql://%s:%s/%s", host, port, database);
            connection = DriverManager.getConnection(
                    jdbcUrl,
                    user,
                    password
            );
            System.out.println("Database connection successful!");
        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }
    }

    @Override
    public Trip findIncompletedTrip(int userId) {
        String query = "SELECT * FROM trips WHERE user_id = ? AND trip_status = 'incomplete' LIMIT 1";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                java.sql.Timestamp sqlTimestamp = resultSet.getTimestamp("estimated_arrival_date_time");
                return new StandardTrip(
                        resultSet.getLong("user_id"),
                        TripStatus.valueOf(resultSet.getString("trip_status")),
                        resultSet.getString("pickup_location"),
                        resultSet.getString("destination"),
                        resultSet.getDouble("distance"),
                        resultSet.getDouble("fare"),
                        resultSet.getString("card_number"),
                        sqlTimestamp,
                        resultSet.getLong("estimated_waiting_time")
                );
            }
        } catch (SQLException e) {
            System.out.println("Failed to find incomplete trip!");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateTripStatus(int tripId, BookingStatus newBookingStatus) {
        String query = "UPDATE trips SET trip_status = ? WHERE trip_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, newBookingStatus.name());
            preparedStatement.setInt(2, tripId);
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("Failed to update trip status!");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean insertNewTrip(Trip trip) {
        String query = "INSERT INTO trips (pickup_location, destination, distance, fare, card_number, estimated_arrival_date_time, estimated_waiting_time, user_id, trip_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, trip.getPickUpLocation());
            preparedStatement.setString(2, trip.getDestination());
            preparedStatement.setDouble(3, trip.getDistance());
            preparedStatement.setDouble(4, trip.getFare());
            preparedStatement.setString(5, trip.getCardNumber());
            preparedStatement.setTimestamp(6, Timestamp.valueOf(trip.getEstimatedArrivalDateTime().toString()));
            preparedStatement.setLong(7, trip.getEstimatedWaitingTime());
            preparedStatement.setLong(8, trip.getUserId());
            preparedStatement.setString(9, trip.getTripStatus().name());

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Failed to insert new trip!");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Trip> findAllTrips(int page, int limit, int userId, List<BookingStatus> statuses, boolean asc) {
        StringBuilder query = new StringBuilder("SELECT * FROM trips WHERE user_id = ?");

        if (statuses != null && !statuses.isEmpty()) {
            query.append(" AND trip_status IN (");
            for (int i = 0; i < statuses.size(); i++) {
                query.append("?");
                if (i < statuses.size() - 1) {
                    query.append(", ");
                }
            }
            query.append(")");
        }

        query.append(" ORDER BY estimated_arrival_date_time ");
        query.append(asc ? "ASC" : "DESC");
        query.append(" LIMIT ? OFFSET ?");

        ArrayList<Trip> trips = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            preparedStatement.setLong(1, userId);
            int index = 2;
            if (statuses != null && !statuses.isEmpty()) {
                for (BookingStatus status : statuses) {
                    preparedStatement.setString(index++, status.name());
                }
            }
            preparedStatement.setInt(index++, limit);
            preparedStatement.setInt(index, ((page - 1) * limit));

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                java.sql.Timestamp sqlTimestamp1 = resultSet.getTimestamp("estimated_arrival_date_time");
                Trip trip = new StandardTrip(
                        resultSet.getLong("user_id"),
                        TripStatus.valueOf(resultSet.getString("trip_status")),
                        resultSet.getString("pickup_location"),
                        resultSet.getString("destination"),
                        resultSet.getDouble("distance"),
                        resultSet.getDouble("fare"),
                        resultSet.getString("card_number"),
                        sqlTimestamp1,
                        resultSet.getLong("estimated_waiting_time")
                );
                trips.add(trip);
            }
        } catch (SQLException e) {
            System.out.println("Failed to retrieve trips!");
            e.printStackTrace();
        }
        return trips;
    }

    @Override
    public int getTotalPages(int limit, int id, List<BookingStatus> statuses) {
        StringBuilder query = new StringBuilder("SELECT COUNT(*) AS total FROM trips WHERE user_id = ?");

        if (statuses != null && !statuses.isEmpty()) {
            query.append(" AND trip_status IN (");
            for (int i = 0; i < statuses.size(); i++) {
                query.append("?");
                if (i < statuses.size() - 1) {
                    query.append(", ");
                }
            }
            query.append(")");
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            preparedStatement.setInt(1, id);
            int index = 2;
            if (statuses != null && !statuses.isEmpty()) {
                for (BookingStatus status : statuses) {
                    preparedStatement.setString(index++, status.name());
                }
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int totalRecords = resultSet.getInt("total");
                return (int) Math.ceil((double) totalRecords / limit);
            }
        } catch (SQLException e) {
            System.out.println("Failed to calculate total pages!");
            e.printStackTrace();
        }
        return 0;
    }
}