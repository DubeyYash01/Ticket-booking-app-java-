package ticket.booking.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ticket.booking.Entities.Train;
import ticket.booking.Entities.User;
import ticket.booking.Utils.UserServiceUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
// import com.google.common.base.Optional;
import java.util.Optional;
import java.util.Scanner;

// import com.fasterxml.jackson.core.JsonParser;
// import com.fasterxml.jackson.core.type.TypeReference;

// import ticket.booking.entities.Ticket;
// import ticket.booking.entities.Train;
// import ticket.booking.entities.User;
// import ticket.booking.util.UserServiceUtil;
// import java.io.File;
// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Paths;
// import java.util.*;

public class UserBookingService {

    private User user;

    private List<User> userList;

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String USERS_PATH = "../localDb/users.json";

    public UserBookingService(User user1) throws IOException {
        this.user = user1;
        loadUsers();
    }

    public UserBookingService() throws IOException {
        loadUsers();
    }

    public List<User> loadUsers() throws IOException {
        File users = new File(USERS_PATH);

        return objectMapper.readValue(users, new TypeReference<List<User>>() {
        });
    }

    public Boolean loginUser() {
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getName().equalsIgnoreCase(user.getName())
                    && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword());
        }).findFirst();
        return foundUser.isPresent();
    }

    // public boolean loginUser(){
    // Optional<User> founUser = userList.stream().filter(user1 -> {
    // return user1.getName().equals(user.getName()) &&
    // UserServiceUtil.checkPassword(user.getPassword() , user1.getHashedPassword())
    // })
    // }

    public Boolean signUp(User user1) {
        try {
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        } catch (IOException ex) {
            return Boolean.FALSE;
        }
    }

    private void saveUserListToFile() throws IOException {
        File usersFile = new File(USERS_PATH);
        objectMapper.writeValue(usersFile, userList);
    }
    // json --> object (User) --> deserialize
    // object (User) --> json --> serialize

    public void fetchBookings() {
        Optional<User> userFetched = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName())
                    && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword());
        }).findFirst();
        if (userFetched.isPresent()) {
            userFetched.get().printTickets();
        }
    }

    // todo: Complete this function
    public Boolean cancelBooking(String ticketId) {

        Scanner s = new Scanner(System.in);
        System.out.println("Enter the ticket id to cancel");
        ticketId = s.next();

        if (ticketId == null || ticketId.isEmpty()) {
            System.out.println("Ticket ID cannot be null or empty.");
            return Boolean.FALSE;
        }

        String finalTicketId1 = ticketId; // Because strings are immutable
        boolean removed = user.getTicketsBooked().removeIf(ticket -> ticket.getTicketId().equals(finalTicketId1));

        String finalTicketId = ticketId;
        user.getTicketsBooked().removeIf(Ticket -> Ticket.getTicketId().equals(finalTicketId));
        if (removed) {
            System.out.println("Ticket with ID " + ticketId + " has been canceled.");
            return Boolean.TRUE;
        } else {
            System.out.println("No ticket found with ID " + ticketId);
            return Boolean.FALSE;
        }
    }

    public List<Train> getTrains(String source, String destination) {
        try {
            TrainService trainService = new TrainService();
            return trainService.searchTrains(source, destination);

        } catch (IOException ex) {
            // TODO: handle exception
            return new ArrayList<>();
        }
    }

}
