package view;

import model.Ticket;
import model.Reservation;
import model.Customer;
import model.Seat;

public class TicketView {

    public void displayTicket(Ticket ticket, String concertName) {

        if (ticket == null) {
            System.out.println("Ticket information is unavailable.");
            return;
        }

        Reservation reservation = ticket.getReservation();
        Customer customer = reservation.getCustomer();
        Seat seat = reservation.getSeat();

        System.out.println("\n========== STAGEPASS TICKET ==========");

        System.out.println("Ticket ID: " + ticket.getTicketId());
        System.out.println("Customer Name: " + customer.getFullName());
        System.out.println("Concert: " + concertName);
        System.out.println("Seat Number: " + seat.getSeatNumber());
        System.out.println("Ticket Category: " + seat.getCategory());
        System.out.printf("Ticket Price: PHP %,.2f%n", ticket.getPrice());
        System.out.println("Date Issued: " + ticket.getIssueDate());

        System.out.println("======================================");
        System.out.println("       Thank you for choosing StagePass!");
    }
}