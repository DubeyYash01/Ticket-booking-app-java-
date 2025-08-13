Ticket Booking App Java
This is a simple console-based Java application designed to simulate a train ticket booking system. It allows users to sign up, log in, search for trains, book seats, view their bookings, and cancel tickets. The application uses local JSON files for data persistence, mimicking a basic database.

 Features
User Management:

Sign Up: Create a new user account.

Login: Authenticate existing users.

Train Search:

Find available trains between a specified source and destination.

Ticket Booking:

Select a train and book an available seat.

Booking Management:

View all bookings made by the logged-in user.

Cancel existing bookings.

 Technologies Used
Java 11+

Gradle: For project automation and dependency management.

 How to Run Locally
This project is configured as a Gradle project and is best run using an IDE like VS Code with the appropriate Java extensions.

Prerequisites
Java Development Kit (JDK) 11 or higher installed on your system.

Visual Studio Code with the Extension Pack for Java installed.

Steps
Clone the repository:
Open your terminal or command prompt and run:

git clone https://github.com/DubeyYash01/Ticket-booking-app-java.git

Navigate into the cloned directory:

cd Ticket-booking-app-java

Open the project in VS Code:

Go to File > Open Folder...

Select the Ticket-booking-app-java folder you just cloned.

Run the application:

In VS Code's Explorer, navigate to app/src/main/java/org/example/App.java.

Open the App.java file in the editor.

Click the "Run" button (a green play icon ▶️) that appears above the main method (around line 20).

The application will start in the integrated terminal, and you can interact with it there.

 Project Structure
The core logic and data are organized within the app/src/main/java directory:

ticket.booking.Entities: Contains data models such as Train, User, and Tickets. These classes define the structure of the data used in the application.

ticket.booking.Utils: Houses utility classes like UserServiceUtil, which might include helper methods for common tasks (e.g., password hashing).

ticket.booking.services: Contains the business logic and service layers of the application, including UserBookingService (for user-related booking actions) and TrainService (for train-related operations).

ticket.booking.localDb: This package contains JSON files (trains.json, users.json) that serve as a simple, local "database" for storing application data.

org.example.App.java: This is the main entry point of the application, containing the main method where the program execution begins.

 Future Enhancements (Ideas)
Implement a more robust database solution (e.g., SQLite, MySQL).

Add more detailed train information (e.g., train type, capacity, class).

Implement seat selection logic with seat maps.

Add date and time-based search for trains.

Develop a graphical user interface (GUI) instead of a console-based interface.

Integrate proper error handling and user feedback messages.
