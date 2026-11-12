
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
    private Ticket latestTicket;

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

            clearScreen();

            mainMenuView.displayMenu();

            int choice = mainMenuView.getMenuChoice();

            // Consume the remaining newline
            scanner.nextLine();

            switch (choice) {

                case 1:
                    concertView.displayConcertDetails(concert);
                    break;

                case 2:
                    seatView.displaySeats(
                            seatRepository.getAllSeats()
                    );
                    break;

                case 3:
                    reserveSeat();
                    break;

                case 4:
                    viewMyTicket();
                    break;

                case 5:
                    running = false;
                    System.out.println(
                            "\nThank you for using StagePass!"
                    );
                    break;

                default:
                    System.out.println(
                            "\nInvalid choice. Please try again."
                    );
            }
            if (running)
            {
                System.out.println(
                    "\nPress Enter to return to the main menu..."
                );
                scanner.nextLine();
            }
        }
    }

    private void reserveSeat() {

        System.out.println("\n===== SEAT RESERVATION =====");

        // Display seats
        seatView.displaySeats(seatRepository.getAllSeats());

        System.out.print("\nEnter seat number: ");
        String seatNumber = scanner.nextLine().trim();

        // Find selected seat
        Seat seat = seatRepository.findSeatByNumber(seatNumber);

        if (seat == null) {
            System.out.println("Seat does not exist!");
            return;
        }

        // Check seat availability
        if (seat.getStatus() != SeatStatus.AVAILABLE) {
            System.out.println("Sorry! This seat is already taken.");
            return;
        }

        // Collect customer information
        System.out.println("\nPlease provide your information.");

        Customer customer = customerView.getCustomerDetails();

        // Register customer
        boolean registered =
                customerService.registerCustomer(customer);

        if (!registered) {
            System.out.println("Unable to register customer.");
            return;
        }

        // Create reservation
        Reservation reservation =
                reservationService.createReservation(
                        customer,
                        seatNumber
                );

        if (reservation == null) {
            System.out.println("Unable to reserve this seat.");
            return;
        }

        System.out.println("\nSeat successfully reserved!");
        System.out.println(
                "Reservation ID: " + reservation.getReservationId()
        );

        // Collect payment information
        Payment payment =
                paymentView.getPaymentDetails(seat.getPrice());

        if (payment == null) {

            reservationService.cancelReservation(
                    reservation.getReservationId()
            );

            System.out.println("Reservation cancelled.");
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

                latestTicket = ticket;

                System.out.println("\nPayment successful!");

                ticketView.displayTicket(
                        ticket,
                        concert.getConcertName()
                );

            } else {
                System.out.println("Unable to generate ticket.");
            }

        } else {

            reservationService.cancelReservation(
                    reservation.getReservationId()
            );

            System.out.println(
                    "Payment failed. Reservation cancelled."
            );
        }
    }

    private void viewMyTicket() {

        System.out.println("\n===== MY TICKET =====");

        if (latestTicket == null) {
            System.out.println("You don't have a ticket yet.");
            return;
        }

        ticketView.displayTicket(
                latestTicket,
                concert.getConcertName()
        );
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}