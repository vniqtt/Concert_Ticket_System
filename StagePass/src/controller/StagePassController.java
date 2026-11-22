
package controller;

import model.Concert;
import model.Seat;
import model.Customer;
import model.Payment;
import model.Reservation;
import model.Ticket;
import model.SeatStatus;

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
                seatRepository
        );

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

    private void initializeSeats() {

        SeatInitializationService initializer =
                new SeatInitializationService();

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
            showAdminMenu();
            continue;
        }

        switch (input) {

            case "1":
                concertView.displayConcertDetails(concert);
                break;

            case "2":
                seatView.displaySeats(
                        seatRepository.getAllSeats()
                );
                break;

            case "3":
                reserveSeat();
                break;

            case "4":
                running = false;
                System.out.println(
                        "\nThank you for using StagePass!"
                );
                break;

            default:
                System.out.println(
                        ConsoleColors.RED
                        + "\nInvalid choice. Please try again."
                        + ConsoleColors.RESET
                );
        }
    }
}

    private void reserveSeat() {

        clearScreen();

        System.out.println("\n===== SEAT RESERVATION =====");

        // Display seats
        seatView.displaySeats(seatRepository.getAllSeats());
        

        
Seat seat = null;
String seatNumber;

while (true) {

    System.out.print("\nEnter seat number (0 to cancel): ");
    seatNumber = scanner.nextLine().trim();

    if (seatNumber.equals("0")) {
        System.out.println("\nReturning to main menu...");
        return;
    }

    seat = seatRepository.findSeatByNumber(seatNumber);

    if (seat == null) {

        System.out.println(
                ConsoleColors.RED
                + "Seat does not exist! Please try again."
                + ConsoleColors.RESET
        );

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        clearScreen();
        seatView.displaySeats(seatRepository.getAllSeats());
        continue;
    }

    if (seat.getStatus() != SeatStatus.AVAILABLE) {

        System.out.println(
                ConsoleColors.RED
                + "Sorry! This seat is unavailable. Please choose another seat."
                + ConsoleColors.RESET
        );

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        clearScreen();

        // Redisplay available seats
        seatView.displaySeats(seatRepository.getAllSeats());

        continue;
    }

    break;
}

        // Collect customer information
        clearScreen();

        System.out.println("\n===== CUSTOMER INFORMATION =====");
        System.out.println("\nPlease provide your information.");

        Customer customer = customerView.getCustomerDetails();

        // Register customer
        boolean registered =
                customerService.registerCustomer(customer);

        if (!registered) {
            System.out.println(ConsoleColors.RED + "Unable to register customer." + ConsoleColors.RESET);
            return;
        }

        // Create reservation
        Reservation reservation =
                reservationService.createReservation(
                        customer,
                        seatNumber
                );

        if (reservation == null) {
            System.out.println(ConsoleColors.RED + "Unable to reserve this seat." + ConsoleColors.RESET);
            return;
        }

        System.out.println("\nSeat successfully reserved!");
        System.out.println(
                "Reservation ID: " + reservation.getReservationId()
        );

        // Collect payment information

        clearScreen();
        Payment payment = paymentView.getPaymentDetails(seat.getPrice());

        if (payment == null) {

            reservationService.cancelReservation(
                    reservation.getReservationId()
            );

            System.out.println(ConsoleColors.RED + "Reservation cancelled." + ConsoleColors.RESET);
            return;
        }

        // Process payment
        boolean successful =
                paymentService.processPayment(reservation, payment);

        if (successful) {

            // Generate ticket
            Ticket ticket =
                    ticketService.generateTicket(reservation);

            if (ticket != null) {

                clearScreen();

                System.out.println("\nPayment successful!");

                ticketView.displayTicket(
                        ticket,
                        concert.getConcertName()
                );

            } else {
                System.out.println(ConsoleColors.RED + "Unable to generate ticket." + ConsoleColors.RESET);
            }

        } else {

            reservationService.cancelReservation(
                    reservation.getReservationId()
            );

            System.out.println(ConsoleColors.RED + "Payment failed. Reservation cancelled." + ConsoleColors.RESET);
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
                adminRunning = false;
                break;

            default:
                System.out.println(
                        ConsoleColors.RED
                        + "Invalid admin choice."
                        + ConsoleColors.RESET
                );
        }
    }
}

private void viewAllPurchases() {

    clearScreen();

    System.out.println(
            ConsoleColors.PURPLE
            + "\n===== ALL PURCHASES ====="
            + ConsoleColors.RESET
    );

    if (ticketRepository.getAllTickets().isEmpty()) {

        System.out.println("\nNo purchases have been made yet.");
        return;
    }

    for (Ticket ticket : ticketRepository.getAllTickets()) {

        System.out.println("\n--------------------------------");

        ticketView.displayTicket(
                ticket,
                concert.getConcertName()
        );
    }

    System.out.println("\n--------------------------------");
}

}