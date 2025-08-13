// package org.example;

// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;
// import java.util.Map;
// import java.util.Scanner;
// import java.util.UUID;
// import java.util.function.Predicate;
// import java.util.stream.Collectors;

// import ticket.booking.Entities.Train;
// import ticket.booking.Entities.User;
// import ticket.booking.Utils.UserServiceUtil;
// import ticket.booking.services.UserBookingService;

// public class App {

//     public static void main(String[] args) {
//         System.out.println("Running Train Bookng System");
//         Scanner scanner = new Scanner(System.in);
//         int option = 0;
//         Object userBookingService;
//         userBookingService = new userBookingService();
//         try {
//             userBookingService = new UserBookingService();
//         } catch (IOException ex) {
//             System.out.println("There is something wrong");
//             return;
//         }

//         while (option != 7) {
//             System.out.println("Please select an option:");
//             System.out.println("1. Login");
//             System.out.println("2. Sign Up");
//             System.out.println("3. fetch bookings");
//             System.out.println("4. Search Train");
//             System.out.println("5. Book Ticket");
//             System.out.println("6. Cancel Ticket");
//             System.out.println("7. Exit");

//             option = scanner.nextInt();
//             Train trainSelectedForBooking;
//             switch (option) {
//                 case 1:
//                     System.out.println("Enter your name:");
//                     String nameToSignUp = scanner.next();
//                     System.out.println("Enter your password:");
//                     String passwordToSignUp = scanner.next();

//                     User userToSignUp = new User(nameToSignUp, passwordToSignUp,
//                             UserServiceUtil.hashedPassword(passwordToSignUp),
//                             new ArrayList<>(), UUID.randomUUID().toString());
//                     ((UserBookingService) userBookingService).signUp(userToSignUp);
//                     break;

//                 case 2:
//                     System.out.println("Enter your name:");
//                     String nameToLogin = scanner.next();
//                     System.out.println("Enter your password:");
//                     String passwordToLogin = scanner.next();

//                     User userToLogin = new User(nameToLogin, passwordToLogin,
//                             UserServiceUtil.hashedPassword(passwordToLogin),
//                             new ArrayList<>(), UUID.randomUUID().toString());

//                     try {
//                         userBookingService = new UserBookingService(userToLogin);
//                     } catch (IOException ex) {
//                         return;
//                     }
//                     break;

//                 case 3:
//                     System.out.println("Fetching your bookings...");
//                     ((UserBookingService) userBookingService).fetchBookings();
//                     break;

//                 case 4:
//                     System.out.println("Type your source station");
//                     String source = scanner.next();
//                     System.out.println("Type your destination station");
//                     String dest = scanner.next();
//                     List<Train> trains = ((UserBookingService) userBookingService).getTrains(source, dest);
//                     int index = 1;
//                     for (Train t : trains) {
//                         System.out.println(index + " Train id : " + t.getTrainId());
//                         for (Map.Entry<String, String> entry : t.getStationTimes().entrySet()) {
//                             System.out.println("station " + entry.getKey() + " time: " + entry.getValue());
//                         }
//                     }
//                     System.out.println("Select a train by typing 1,2,3...");
//                     trainSelectedForBooking = trains.get(scanner.nextInt());
//                     break;

//                 case 5:
//                     System.out.println("Select a seat out of these seats");
//                     List<List<Integer>> seats = ((Object) userBookingService).fetchSeats(trainSelectedForBooking);
//                     for (List<Integer> row : seats) {
//                         for (Integer val : row) {
//                             System.out.print(val + " ");
//                         }
//                         System.out.println();
//                     }
//                     System.out.println("Select the seat by typing the row and column");
//                     System.out.println("Enter the row");
//                     int row = scanner.nextInt();
//                     System.out.println("Enter the column");
//                     int col = scanner.nextInt();
//                     System.out.println("Booking your seat....");
//                     Boolean booked = userBookingService.bookTrainSeat(trainSelectedForBooking, row, col);
//                     if (booked.equals(Boolean.TRUE)) {
//                         System.out.println("Booked! Enjoy your journey");
//                     } else {
//                         System.out.println("Can't book this seat");
//                     }
//                     break;
//                 default:
//                     break;

//             }

//         }
//     }
// }

package app.src.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays; // Not used in the provided code, consider removing if truly unused.
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.function.Predicate; // Not used, consider removing.
import java.util.stream.Collectors; // Not used, consider removing.

// These imports were causing 'package does not exist' errors because App.java was in the test directory.
// Moving App.java to src/main/java will resolve these once the project is properly recognized.
import ticket.booking.Entities.Train;
import ticket.booking.Entities.User;
import ticket.booking.Utils.UserServiceUtil;
import ticket.booking.services.UserBookingService;

public class App {

    public static void main(String[] args) {
        System.out.println("Running Train Booking System");
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        // Changed type from Object to UserBookingService, as it will be initialized as
        // such.
        // This removes the need for multiple explicit casts later.
        UserBookingService userBookingService = null; // Initialize to null

        try {
            // Corrected typo: Class names start with a capital letter (UserBookingService)
            userBookingService = new UserBookingService();
        } catch (IOException ex) {
            System.out.println("There is something wrong: " + ex.getMessage());
            ex.printStackTrace(); // Print stack trace for debugging
            return;
        }

        while (option != 7) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Sign Up"); // Renamed from Login to Sign Up based on case 1 logic
            System.out.println("2. Login"); // Renamed from Sign Up to Login based on case 2 logic
            System.out.println("3. Fetch Bookings");
            System.out.println("4. Search Train");
            System.out.println("5. Book Ticket");
            System.out.println("6. Cancel Ticket");
            System.out.println("7. Exit");

            option = scanner.nextInt();
            // Consume the rest of the line to prevent issues with nextLine() after
            // nextInt()
            scanner.nextLine();

            Train trainSelectedForBooking = null; // Initialize to null to avoid potential uninitialized variable error

            switch (option) {
                case 1: // Sign Up
                    System.out.println("Enter your desired username:");
                    String nameToSignUp = scanner.nextLine();
                    System.out.println("Enter your desired password:");
                    String passwordToSignUp = scanner.nextLine();

                    // Ensure UserServiceUtil is correctly implemented to hash passwords.
                    // The 'new ArrayList<>()' for bookings and UUID.randomUUID() are fine.
                    User userToSignUp = new User(nameToSignUp, passwordToSignUp,
                            UserServiceUtil.hashedPassword(passwordToSignUp),
                            new ArrayList<>(), UUID.randomUUID().toString());

                    // No cast needed anymore as userBookingService is of type UserBookingService
                    userBookingService.signUp(userToSignUp);
                    System.out.println("Signed up successfully!");
                    break;

                case 2: // Login
                    System.out.println("Enter your username:");
                    String nameToLogin = scanner.nextLine();
                    System.out.println("Enter your password:");
                    String passwordToLogin = scanner.nextLine();

                    User userToLogin = new User(nameToLogin, passwordToLogin,
                            UserServiceUtil.hashedPassword(passwordToLogin),
                            new ArrayList<>(), UUID.randomUUID().toString());

                    try {
                        // Corrected: Initialize UserBookingService with the logged-in user if needed
                        // This implies UserBookingService has a constructor that takes a User object.
                        // If it's meant to be a singleton or manage multiple users, its implementation
                        // might need adjustment.
                        // Assuming this constructor sets the 'currentUser' for the session.
                        userBookingService = new UserBookingService(userToLogin);
                        System.out.println("Logged in successfully!");
                    } catch (IOException ex) {
                        System.out.println("Login failed: " + ex.getMessage());
                        ex.printStackTrace();
                        // It's usually better not to 'return' here, but to give the user another
                        // chance.
                        // For now, keeping your original flow.
                    }
                    break;

                case 3: // Fetch Bookings
                    if (userBookingService == null || userBookingService.getCurrentUser() == null) {
                        System.out.println("Please login first to fetch bookings.");
                        break;
                    }
                    System.out.println("Fetching your bookings...");
                    userBookingService.fetchBookings(); // No cast needed
                    break;

                case 4: // Search Train
                    System.out.println("Type your source station:");
                    String source = scanner.nextLine();
                    System.out.println("Type your destination station:");
                    String dest = scanner.nextLine();

                    if (userBookingService == null) {
                        System.out.println("Service not initialized. Please consider logging in or signing up first.");
                        break;
                    }

                    List<Train> trains = userBookingService.getTrains(source, dest); // No cast needed
                    if (trains.isEmpty()) {
                        System.out.println("No trains found for the given route.");
                        break;
                    }

                    System.out.println("Available Trains:");
                    for (int i = 0; i < trains.size(); i++) {
                        Train t = trains.get(i);
                        System.out.println((i + 1) + ". Train ID: " + t.getTrainId());
                        for (Map.Entry<String, String> entry : t.getStationTimes().entrySet()) {
                            System.out.println("   Station " + entry.getKey() + " time: " + entry.getValue());
                        }
                    }
                    System.out.println("Select a train by typing its number (1, 2, 3...):");

                    int trainChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (trainChoice > 0 && trainChoice <= trains.size()) {
                        trainSelectedForBooking = trains.get(trainChoice - 1); // Adjust for 0-based index
                    } else {
                        System.out.println("Invalid train selection.");
                    }
                    break;

                case 5: // Book Ticket
                    if (userBookingService == null || userBookingService.getCurrentUser() == null) {
                        System.out.println("Please login first to book a ticket.");
                        break;
                    }
                    if (trainSelectedForBooking == null) {
                        System.out.println("Please search for and select a train first (Option 4).");
                        break;
                    }

                    System.out.println("Available seats for Train " + trainSelectedForBooking.getTrainId() + ":");
                    // Assuming fetchSeats returns List<List<Integer>>
                    // userBookingService is already of type UserBookingService
                    List<List<Integer>> seats = userBookingService.fetchSeats(trainSelectedForBooking);
                    if (seats.isEmpty()) {
                        System.out.println("No seats available or seats could not be fetched.");
                        break;
                    }

                    for (List<Integer> row : seats) {
                        for (Integer val : row) {
                            System.out.print(val + " ");
                        }
                        System.out.println();
                    }
                    System.out.println("Select the seat by typing the row and column (e.g., 1 5 for row 1, column 5):");
                    System.out.println("Enter the row:");
                    int row = scanner.nextInt();
                    System.out.println("Enter the column:");
                    int col = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    System.out.println("Booking your seat....");
                    // Assuming bookTrainSeat is a method of UserBookingService
                    Boolean booked = userBookingService.bookTrainSeat(trainSelectedForBooking, row, col);
                    if (booked.equals(Boolean.TRUE)) {
                        System.out.println("Booked! Enjoy your journey.");
                    } else {
                        System.out.println("Can't book this seat. It might be taken or invalid.");
                    }
                    break;

                case 6: // Cancel Ticket
                    if (userBookingService == null || userBookingService.getCurrentUser() == null) {
                        System.out.println("Please login first to cancel a ticket.");
                        break;
                    }
                    System.out.println("Enter the booking ID to cancel:");
                    String bookingIdToCancel = scanner.nextLine();
                    // Assuming you have a method like cancelBooking in UserBookingService
                    boolean cancelled = userBookingService.cancelBooking(bookingIdToCancel);
                    if (cancelled) {
                        System.out.println("Booking cancelled successfully!");
                    } else {
                        System.out.println("Failed to cancel booking. Please check the booking ID or try again.");
                    }
                    break;

                case 7: // Exit
                    System.out.println("Exiting Train Booking System. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
        scanner.close(); // Close the scanner when done
    }
}
