
package controller;

import model.Concert;
import model.Seat;
import model.Customer;
import model.Payment;
import model.Reservation;
import model.Ticket;
import model.SeatStatus;
import model.TicketCategory;

import repository.SeatRepository;
import repository.CustomerRepository;
import repository.ReservationRepository;
import repository.PaymentRepository;
import repository.TicketRepository;

import service.SeatInitializationService;
import service.CustomerService;
import service.ReservationService;
import service.PaymentService;
import service.TicketService;

import view.MainMenuView;
import view.ConcertView;
import view.ConsoleColors;
import view.SeatView;
import view.CustomerView;
import view.PaymentView;
import view.TicketView;

import java.util.Scanner;

public class StagePassController {

    private Scanner scanner;
    private Concert concert;

    // Repositories
    private SeatRepository seatRepository;
    private CustomerRepository customerRepository;
    private ReservationRepository reservationRepository;
    private PaymentRepository paymentRepository;
    private TicketRepository ticketRepository;

    // Services
    private CustomerService customerService;
    private ReservationService reservationService;
    private PaymentService paymentService;
    private TicketService ticketService;

    // Views
    private MainMenuView mainMenuView;
    private ConcertView concertView;
    private SeatView seatView;
    private CustomerView customerView;
    private PaymentView paymentView;
    private TicketView ticketView;

    // Store the latest generated ticket

    public StagePassController(Concert concert) {

        this.scanner = new Scanner(System.in);
        this.concert = concert;

        // Initialize repositories
        seatRepository = new SeatRepository();
        customerRepository = new CustomerRepository();
        reservationRepository = new ReservationRepository();
        paymentRepository = new PaymentRepository();
        ticketRepository = new TicketRepository();

        // Initialize services
        customerService = new CustomerService(customerRepository);

        reservationService = new ReservationService(
                reservationRepository,
                seatRepository);

        paymentService = new PaymentService(paymentRepository);
        ticketService = new TicketService(ticketRepository);

        // Initialize views
        mainMenuView = new MainMenuView(scanner);
        concertView = new ConcertView();
        seatView = new SeatView();
        customerView = new CustomerView(scanner);
        paymentView = new PaymentView(scanner);
        ticketView = new TicketView();

        initializeSeats();
    }

    
private TicketCategory selectTicketCategory() {

    while (true) {

        clearScreen();

        System.out.println(
            ConsoleColors.CYAN
            + "\n===== SELECT TICKET CATEGORY ====="
            + ConsoleColors.RESET
        );

        System.out.println(
            ConsoleColors.WHITE
            + "1. "
            + ConsoleColors.PURPLE
            + "DIAMOND"
            + ConsoleColors.WHITE
            + " - PHP "
            + String.format("%,d", TicketCategory.DIAMOND.getPrice())
            + ConsoleColors.RESET
        );

        System.out.println(
            ConsoleColors.WHITE
            + "2. "
            + ConsoleColors.YELLOW
            + "VIP"
            + ConsoleColors.WHITE
            + " - PHP "
            + String.format("%,d", TicketCategory.VIP.getPrice())
            + ConsoleColors.RESET
        );

        System.out.println(
            ConsoleColors.WHITE
            + "3. "
            + ConsoleColors.CYAN
            + "PLATINUM"
            + ConsoleColors.WHITE
            + " - PHP "
            + String.format("%,d", TicketCategory.PLATINUM.getPrice())
            + ConsoleColors.RESET
        );

        System.out.println(
            ConsoleColors.WHITE
            + "4. "
            + ConsoleColors.GREEN
            + "GOLD"
            + ConsoleColors.WHITE
            + " - PHP "
            + String.format("%,d", TicketCategory.GOLD.getPrice())
            + ConsoleColors.RESET
        );

        System.out.println(
            "5. "
            + "SILVER"
            + " - PHP "
            + String.format("%,d", TicketCategory.SILVER.getPrice())
        );

        System.out.println(
            ConsoleColors.RED
            + "0. Cancel"
            + ConsoleColors.RESET
        );

        System.out.print(
            ConsoleColors.CYAN
            + "\nChoose category: "
            + ConsoleColors.RESET
        );

        String choice = scanner.nextLine().trim();

        switch (choice) {

            case "1":
                return TicketCategory.DIAMOND;

            case "2":
                return TicketCategory.VIP;

            case "3":
                return TicketCategory.PLATINUM;

            case "4":
                return TicketCategory.GOLD;

            case "5":
                return TicketCategory.SILVER;

            case "0":
                return null;

            default:
                System.out.println(
                    ConsoleColors.RED
                    + "\nInvalid category. Please try again."
                    + ConsoleColors.RESET
                );

                pauseBeforeContinuing();
                break;
        }
    }
}


    
private void displaySeatsByCategory(TicketCategory category) {

    System.out.println(
        ConsoleColors.CYAN
        + "\n===== " + category + " SEATING ====="
        + ConsoleColors.RESET
    );

    System.out.println(
        ConsoleColors.WHITE
        + "Seat Number | Price | Status"
        + ConsoleColors.RESET
    );

    System.out.println("--------------------------------------");

    for (Seat seat : seatRepository.getAllSeats()) {

        if (seat.getCategory() == category) {

            String status;
            String statusColor;

            if (seat.getStatus() == SeatStatus.AVAILABLE) {

                status = "AVAILABLE";
                statusColor = ConsoleColors.GREEN;

            } else if (seat.getStatus() == SeatStatus.RESERVED) {

                status = "RESERVED";
                statusColor = ConsoleColors.YELLOW;

            } else {

                status = "SOLD";
                statusColor = ConsoleColors.RED;
            }

            System.out.println(
                ConsoleColors.WHITE
                + seat.getSeatNumber()
                + " | PHP "
                + String.format("%,d", seat.getPrice())
                + " | "
                + statusColor
                + status
                + ConsoleColors.RESET
            );
        }
    }

    System.out.println("--------------------------------------");

    // Status legend
    System.out.println(
        ConsoleColors.GREEN + "AVAILABLE"
        + ConsoleColors.RESET
        + " | "
        + ConsoleColors.YELLOW + "RESERVED"
        + ConsoleColors.RESET
        + " | "
        + ConsoleColors.RED + "SOLD"
        + ConsoleColors.RESET
    );
}


    private void initializeSeats() {

        SeatInitializationService initializer = new SeatInitializationService();

        initializer.initializeSeats(concert);

        for (Seat seat : concert.getSeats()) {
            seatRepository.addSeat(seat);
        }
    }

    public void start() {

        boolean running = true;

        while (running) {

            mainMenuView.displayMenu();

            String input = mainMenuView.getMenuInput();

            clearScreen();

            // Check for admin command
            if (input.equalsIgnoreCase("admin")) {
                System.out.print("Enter Admin PIN: ");
                String pin = scanner.nextLine();

                if (pin.equals("1234")) {
                    System.out.println("Access Granted!");
                    showAdminMenu();
                } else {
                    System.out.println("Incorrect PIN! Access Denied.");
                }

                continue;
            }

            switch (input) {

                case "1":
                    concertView.displayConcertDetails(concert);
                    break;

                case "2":
                    viewAvailableSeats();
                    break;

                case "3":
                    reserveSeat();
                    break;

                case "4":
                    running = false;
                    System.out.println(
                            "\nThank you for using StagePass!");
                    break;

                default:
                    System.out.println(
                            ConsoleColors.RED
                                    + "\nInvalid choice. Please try again."
                                    + ConsoleColors.RESET);
            }
        }
    }

    // VIEW AVAILABLE SEATS BY CATEGORY
    private void viewAvailableSeats() {

        while (true) {

            TicketCategory selectedCategory = selectTicketCategory();

            if (selectedCategory == null) {
                System.out.println("\nReturning to main menu...");
                return;
            }

            clearScreen();

            displaySeatsByCategory(selectedCategory);

            System.out.println("\nPress Enter to view another category...");
            System.out.println("Enter 0 to return to main menu.");

            String choice = scanner.nextLine().trim();

            if (choice.equals("0")) {
                return;
            }
        }
    }

    private void reserveSeat() {

        clearScreen();

        System.out.println("\n===== SEAT RESERVATION =====");

        // STEP 1: Select ticket category
        TicketCategory selectedCategory = selectTicketCategory();

        if (selectedCategory == null) {
            System.out.println("\nReturning to main menu...");
            return;
        }

        // STEP 2: Display seats from selected category
        Seat seat = null;
        String seatNumber;

        while (true) {

            clearScreen();

            displaySeatsByCategory(selectedCategory);

            // STEP 3: Ask customer to select a seat
            System.out.print("\nEnter seat number (0 to cancel): ");
            seatNumber = scanner.nextLine().trim();

            if (seatNumber.equals("0")) {
                System.out.println("\nReservation cancelled.");
                return;
            }

            // Find selected seat
            seat = seatRepository.findSeatByNumber(seatNumber);

            // Check if seat exists
            if (seat == null) {

                System.out.println(
                        ConsoleColors.RED
                                + "\nSeat does not exist!"
                                + ConsoleColors.RESET);

                pauseBeforeContinuing();
                continue;
            }

            // Check if seat belongs to selected category
            if (seat.getCategory() != selectedCategory) {

                System.out.println(
                        ConsoleColors.RED
                                + "\nPlease choose a seat from the selected category!"
                                + ConsoleColors.RESET);

                pauseBeforeContinuing();
                continue;
            }

            // Check seat availability
            if (seat.getStatus() != SeatStatus.AVAILABLE) {

                System.out.println(
                        ConsoleColors.RED
                                + "\nSorry! This seat is unavailable. Please choose another seat."
                                + ConsoleColors.RESET);

                pauseBeforeContinuing();
                continue;
            }

            break;
        }

        // STEP 4: Collect customer information
        clearScreen();

        System.out.println("\n===== CUSTOMER INFORMATION =====");
        System.out.println("\nPlease provide your information.");

        Customer customer = customerView.getCustomerDetails();

        // Register customer
        boolean registered = customerService.registerCustomer(customer);

        if (!registered) {

            System.out.println(
                    ConsoleColors.RED
                            + "Unable to register customer."
                            + ConsoleColors.RESET);

            return;
        }

        // STEP 5: Create reservation
        Reservation reservation = reservationService.createReservation(
                customer,
                seatNumber);

        if (reservation == null) {

            System.out.println(
                    ConsoleColors.RED
                            + "Unable to reserve this seat."
                            + ConsoleColors.RESET);

            return;
        }

        System.out.println("\nSeat successfully reserved!");

        System.out.println(
                "Reservation ID: " + reservation.getReservationId());

        // STEP 6: Collect payment information
        clearScreen();

        Payment payment = paymentView.getPaymentDetails(seat.getPrice());

        if (payment == null) {

            reservationService.cancelReservation(
                    reservation.getReservationId());

            System.out.println(
                    ConsoleColors.RED
                            + "Reservation cancelled."
                            + ConsoleColors.RESET);

            return;
        }

        // STEP 7: Process payment
        boolean successful = paymentService.processPayment(reservation, payment);

        if (successful) {

            // Generate ticket
            Ticket ticket = ticketService.generateTicket(reservation);

            if (ticket != null) {

                clearScreen();

                System.out.println("\nPayment successful!");

                ticketView.displayTicket(
                        ticket,
                        concert.getConcertName());

            } else {

                System.out.println(
                        ConsoleColors.RED
                                + "Unable to generate ticket."
                                + ConsoleColors.RESET);
            }

        } else {

            reservationService.cancelReservation(
                    reservation.getReservationId());

            System.out.println(
                    ConsoleColors.RED
                            + "Payment failed. Reservation cancelled."
                            + ConsoleColors.RESET);
        }
    }

    private void pauseBeforeContinuing() {

        try {
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls")
                    .inheritIO()
                    .start()
                    .waitFor();
        } catch (Exception e) {
            System.out.println("Unable to clear the screen.");
        }
    }

    private void showAdminMenu() {

        boolean adminRunning = true;

        while (adminRunning) {

            mainMenuView.displayAdminMenu();

            String choice = mainMenuView.getMenuInput();

            clearScreen();

            switch (choice) {

                case "1":
                    viewAllPurchases();
                    break;

                case "2":
                    editPurchase();
                    break;

                case "3":
                    deletePurchase();
                    break;

                case "4":
                    updateSeatStatus();
                    break;

                case "5":
                    adminRunning = false;
                    break;

                default:
                    System.out.println(
                            ConsoleColors.RED
                                    + "Invalid admin choice."
                                    + ConsoleColors.RESET);
            }
        }
    }

    private void viewAllPurchases() {

        clearScreen();

        System.out.println(
                ConsoleColors.PURPLE
                        + "\n===== ALL PURCHASES ====="
                        + ConsoleColors.RESET);

        if (ticketRepository.getAllTickets().isEmpty()) {

            System.out.println("\nNo purchases have been made yet.");
            return;
        }

        for (Ticket ticket : ticketRepository.getAllTickets()) {

            System.out.println("\n--------------------------------");

            ticketView.displayTicket(
                    ticket,
                    concert.getConcertName());
        }

        System.out.println("\n--------------------------------");
    }

    private void editPurchase() {

        clearScreen();

        System.out.println(
                ConsoleColors.PURPLE
                        + "\n===== EDIT PURCHASE ====="
                        + ConsoleColors.RESET);

        if (ticketRepository.getAllTickets().isEmpty()) {
            System.out.println("\nNo purchases available to edit.");
            return;
        }

        // Display all purchases
        viewAllPurchases();

        System.out.print("\nEnter Ticket ID to edit: ");
        String ticketId = scanner.nextLine().trim();

        // Find the ticket
        Ticket ticket = ticketRepository.findTicketById(ticketId);

        if (ticket == null) {
            System.out.println(
                    ConsoleColors.RED
                            + "\nTicket not found!"
                            + ConsoleColors.RESET);
            return;
        }

        // Get the customer connected to the ticket
        Customer customer = ticket.getReservation().getCustomer();

        System.out.println("\n===== CURRENT CUSTOMER INFORMATION =====");
        System.out.println("Name: " + customer.getFullName());
        System.out.println("Email: " + customer.getEmail());
        System.out.println("Contact Number: " + customer.getContactNumber());

        // Get updated information
        System.out.println("\n===== ENTER UPDATED INFORMATION =====");

        System.out.print("New Full Name: ");
        String name = scanner.nextLine().trim();

        System.out.print("New Email: ");
        String email = scanner.nextLine().trim();

        System.out.print("New Contact Number: ");
        String contactNumber = scanner.nextLine().trim();

        // Update the existing customer
        customer.setFullName(name);
        customer.setEmail(email);
        customer.setContactNumber(contactNumber);

        System.out.println(
                ConsoleColors.GREEN
                        + "\nPurchase information updated successfully!"
                        + ConsoleColors.RESET);
    }

    private void deletePurchase() {

        clearScreen();

        System.out.println(
                ConsoleColors.PURPLE
                        + "\n===== DELETE PURCHASE ====="
                        + ConsoleColors.RESET);

        if (ticketRepository.getAllTickets().isEmpty()) {
            System.out.println("\nNo purchases available to delete.");
            return;
        }

        // Display all purchases
        viewAllPurchases();

        System.out.print("\nEnter Ticket ID to delete: ");
        String ticketId = scanner.nextLine().trim();

        // Find the ticket
        Ticket ticket = ticketRepository.findTicketById(ticketId);

        if (ticket == null) {
            System.out.println(
                    ConsoleColors.RED
                            + "\nTicket not found!"
                            + ConsoleColors.RESET);
            return;
        }

        // Get related objects
        Reservation reservation = ticket.getReservation();
        Customer customer = reservation.getCustomer();
        Seat seat = reservation.getSeat();

        System.out.println("\nTicket ID: " + ticket.getTicketId());
        System.out.println("Customer: " + customer.getFullName());
        System.out.println("Seat Number: " + seat.getSeatNumber());

        System.out.print("\nAre you sure you want to delete this purchase? (Y/N): ");
        String confirmation = scanner.nextLine().trim();

        if (!confirmation.equalsIgnoreCase("Y")) {
            System.out.println("\nDeletion cancelled.");
            return;
        }

        // Release the seat
        seat.setStatus(SeatStatus.AVAILABLE);

        // Remove related records
        ticketRepository.removeTicket(ticket);
        reservationRepository.removeReservation(reservation);
        customerRepository.removeCustomer(customer);

        System.out.println(
                ConsoleColors.GREEN
                        + "\nPurchase deleted successfully!"
                        + ConsoleColors.RESET);

        System.out.println("Seat " + seat.getSeatNumber() + " is now AVAILABLE.");
    }

    private void updateSeatStatus() {

        clearScreen();

        System.out.println(
                ConsoleColors.PURPLE
                        + "\n===== UPDATE SEAT STATUS ====="
                        + ConsoleColors.RESET);

        // Display all seats
        seatView.displaySeats(seatRepository.getAllSeats());

        System.out.print("\nEnter Seat Number: ");
        String seatNumber = scanner.nextLine().trim();

        // Find the seat
        Seat seat = seatRepository.findSeatByNumber(seatNumber);

        if (seat == null) {
            System.out.println(
                    ConsoleColors.RED
                            + "\nSeat not found!"
                            + ConsoleColors.RESET);
            return;
        }

        System.out.println("\nCurrent Status: " + seat.getStatus());

        System.out.println("\nAvailable Statuses:");
        System.out.println("1. AVAILABLE");
        System.out.println("2. RESERVED");
        System.out.println("3. SOLD");

        System.out.print("\nEnter new status: ");
        String choice = scanner.nextLine().trim();

        SeatStatus newStatus;

        switch (choice) {

            case "1":
                newStatus = SeatStatus.AVAILABLE;
                break;

            case "2":
                newStatus = SeatStatus.RESERVED;
                break;

            case "3":
                newStatus = SeatStatus.SOLD;
                break;

            default:
                System.out.println(
                        ConsoleColors.RED
                                + "\nInvalid status choice!"
                                + ConsoleColors.RESET);
                return;
        }

        // Update seat status
        seat.setStatus(newStatus);

        System.out.println(
                ConsoleColors.GREEN
                        + "\nSeat status updated successfully!"
                        + ConsoleColors.RESET);

        System.out.println(
                "Seat " + seat.getSeatNumber()
                        + " is now " + seat.getStatus());
    }
}