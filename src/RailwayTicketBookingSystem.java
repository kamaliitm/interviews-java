import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
//import com.rabbitmq.client.*;

class Seat {
    private int seatNumber;

    public Seat(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }
}

class Coach {
    private int coachNumber;
    private List<Seat> seats;

    public Coach(int coachNumber, int totalSeats) {
        this.coachNumber = coachNumber;
        this.seats = new ArrayList<>();
        for (int i = 1; i <= totalSeats; i++) {
            seats.add(new Seat(i));
        }
    }

    public List<Seat> getSeats() {
        return new ArrayList<>(seats);
    }

    public int getCoachNumber() {
        return coachNumber;
    }
}

class Train {
    private int trainId;
    private int startStation;
    private int destinationStation;
    private List<Coach> coaches;

    public Train(int trainId, int startStation, int destinationStation, List<Coach> coaches) {
        this.trainId = trainId;
        this.startStation = startStation;
        this.destinationStation = destinationStation;
        this.coaches = new ArrayList<>(coaches);
    }

    public int getTrainId() {
        return trainId;
    }

    public int getStartStation() {
        return startStation;
    }

    public int getDestinationStation() {
        return destinationStation;
    }

    public List<Coach> getCoaches() {
        return new ArrayList<>(coaches);
    }
}


class RailwayTicketBookingSystem {
    private List<Train> trains;

    public RailwayTicketBookingSystem() {
        this.trains = new ArrayList<>();
    }

    public void addTrain(Train train) {
        trains.add(train);
    }

    public void removeTrain(int trainId) {
        trains.removeIf(train -> train.getTrainId() == trainId);
    }

    public Set<Integer> getAvailableSeatsBetweenStations(int trainId, int userStart, int userDestination) {
        Train train = findTrainById(trainId);
        if (train == null || userStart < train.getStartStation() || userDestination > train.getDestinationStation()) {
            throw new IllegalArgumentException("Invalid train or user stations.");
        }

        Set<Integer> bookedSeatsBetweenStations = new HashSet<>();
        for (int station = userStart; station < userDestination; station++) {
            for (Coach coach : train.getCoaches()) {
                bookedSeatsBetweenStations.addAll(getBookedSeatsForStation(train, coach, station));
            }
        }

        Set<Integer> availableSeatsBetweenStations = new HashSet<>();
        for (Coach coach : train.getCoaches()) {
            for (Seat seat : coach.getSeats()) {
                int seatNumber = seat.getSeatNumber();
                if (!bookedSeatsBetweenStations.contains(seatNumber)) {
                    availableSeatsBetweenStations.add(seatNumber);
                }
            }
        }

        return availableSeatsBetweenStations;
    }

    private Set<Integer> getBookedSeatsForStation(Train train, Coach coach, int station) {
        Set<Integer> bookedSeatsForStation = new HashSet<>();
        for (Seat seat : coach.getSeats()) {
            int seatNumber = seat.getSeatNumber();
            if (isStationWithinBookingRange(train, seatNumber, station)) {
                bookedSeatsForStation.add(seatNumber);
            }
        }
        return bookedSeatsForStation;
    }

    private boolean isStationWithinBookingRange(Train train, int seatNumber, int station) {
        int seatStationStart = seatNumber + train.getStartStation() - 1;
        int seatStationEnd = seatNumber + train.getStartStation();
        return station >= seatStationStart && station < seatStationEnd;
    }

    public Map<Integer, Set<Integer>> getAvailableSeatsForAllTrains(int userStart, int userDestination) {
        Map<Integer, Set<Integer>> availableSeatsByTrain = new HashMap<>();

        for (Train train : trains) {
            Set<Integer> availableSeats = getAvailableSeatsBetweenStations(train.getTrainId(), userStart, userDestination);
            availableSeatsByTrain.put(train.getTrainId(), availableSeats);
        }

        return availableSeatsByTrain;
    }

    private Train findTrainById(int trainId) {
        return trains.stream().filter(train -> train.getTrainId() == trainId).findFirst().orElse(null);
    }

    // Pessimistic Locking Example
    public boolean bookTicketWithPessimisticLocking(int userId, int trainId, int seatNumber) {
        Connection connection = null;
        try {
            // Establish a database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "username", "password");

            // Begin a transaction
            connection.setAutoCommit(false);

            // Acquire a lock on the seat record for the specified train
            lockSeat(connection, trainId, seatNumber);

            // Perform the booking (update seat status, process payment, etc.)
            boolean bookingSuccess = performBooking(connection, userId, trainId, seatNumber);

            if (bookingSuccess) {
                // Commit the transaction if the booking is successful
                connection.commit();
            } else {
                // Rollback the transaction if the booking fails
                connection.rollback();
            }

            // Release the lock
            releaseSeatLock(connection, trainId, seatNumber);

            return bookingSuccess;
        } catch (SQLException e) {
            // Handle exceptions (logging, error messages, etc.)
            e.printStackTrace();
            return false;
        } finally {
            try {
                // Close the database connection
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Acquire a lock on the seat record for the specified train
    private void lockSeat(Connection connection, int trainId, int seatNumber) throws SQLException {
        String lockSeatQuery = "SELECT * FROM seats WHERE train_id = ? AND seat_number = ? FOR UPDATE";
        try (PreparedStatement preparedStatement = connection.prepareStatement(lockSeatQuery)) {
            preparedStatement.setInt(1, trainId);
            preparedStatement.setInt(2, seatNumber);
            preparedStatement.executeQuery();
        }
    }

    // Perform the booking (update seat status, process payment, etc.)
    private boolean performBooking(Connection connection, int userId, int trainId, int seatNumber) throws SQLException {
        // Example: Update seat status and process payment
        // Implement the necessary logic based on your requirements
        // ...

        return true; // Assume booking is successful for demonstration
    }

    // Release the lock on the seat record for the specified train
    private void releaseSeatLock(Connection connection, int trainId, int seatNumber) throws SQLException {
        // The lock is automatically released when the transaction is committed or rolled back
    }


    // Queue-Based Processing Example
//    private final static String QUEUE_NAME = "booking_queue";
//
//    public void enqueueBookingRequest(int userId, int trainId, int seatNumber) {
//        try {
//            ConnectionFactory factory = new ConnectionFactory();
//            factory.setHost("localhost");
//            try (Connection connection = factory.newConnection();
//                 Channel channel = connection.createChannel()) {
//
//                // Declare a queue
//                channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//
//                // Prepare booking request data
//                String message = userId + ":" + trainId + ":" + seatNumber;
//
//                // Publish the booking request to the queue
//                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
//                System.out.println(" [x] Sent '" + message + "'");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Consumer code to process booking requests from the queue
//    public void startBookingConsumer() {
//        try {
//            ConnectionFactory factory = new ConnectionFactory();
//            factory.setHost("localhost");
//            try (Connection connection = factory.newConnection();
//                 Channel channel = connection.createChannel()) {
//
//                // Declare a queue
//                channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//
//                // Set up a consumer to process booking requests
//                DeliverCallback deliverCallback = (consumerTag, delivery) -> {
//                    String message = new String(delivery.getBody(), "UTF-8");
//                    System.out.println(" [x] Received '" + message + "'");
//
//                    // Process the booking request
//                    processBookingRequest(message);
//                };
//
//                // Consume messages from the queue
//                channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
//                });
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Process the booking request (implementation specific to your requirements)
//    private void processBookingRequest(String message) {
//        // Implement the necessary logic to process the booking request
//        // Example: Extract userId, trainId, seatNumber from the message and perform booking
//        // ...
//    }

    public static void main(String[] args) {
        RailwayTicketBookingSystem bookingSystem = new RailwayTicketBookingSystem();

        List<Coach> coaches1 = new ArrayList<>();
        coaches1.add(new Coach(1, 20));
        coaches1.add(new Coach(2, 25));

        Train train1 = new Train(1, 1, 5, coaches1);
        bookingSystem.addTrain(train1);

        List<Coach> coaches2 = new ArrayList<>();
        coaches2.add(new Coach(1, 15));
        coaches2.add(new Coach(2, 30));

        Train train2 = new Train(2, 3, 8, coaches2);
        bookingSystem.addTrain(train2);

        int userStart = 2;
        int userDestination = 4;

        Map<Integer, Set<Integer>> availableSeatsByTrain = bookingSystem.getAvailableSeatsForAllTrains(userStart, userDestination);

        for (Map.Entry<Integer, Set<Integer>> entry : availableSeatsByTrain.entrySet()) {
            int trainId = entry.getKey();
            Set<Integer> availableSeats = entry.getValue();
            System.out.println("Train " + trainId + ": Available seats between user stations " + userStart + " and " + userDestination + ": " + availableSeats);
        }
    }
}
