package view;

import model.Ticket;
import model.Reservation;
import model.Customer;
import model.Seat;

public class TicketView {

    public void displayTicket(Ticket ticket, String concertName) {

        if (ticket == null) {
            System.out.println(
                ConsoleColors.RED + "Ticket information is unavailable."
                + ConsoleColors.RESET
            );
            return;
        }

        Reservation reservation = ticket.getReservation();
        Customer customer = reservation.getCustomer();
        Seat seat = reservation.getSeat();

        System.out.println(
            ConsoleColors.CYAN +
            "\n========== STAGEPASS TICKET =========="
            + ConsoleColors.RESET
        );

        System.out.println(
            ConsoleColors.YELLOW + "Ticket ID: "
            + ConsoleColors.WHITE + ticket.getTicketId()
            + ConsoleColors.RESET
        );

        System.out.println(
            ConsoleColors.YELLOW + "Customer Name: "
            + ConsoleColors.WHITE + customer.getFullName()
            + ConsoleColors.RESET
        );

        System.out.println(
            ConsoleColors.YELLOW + "Concert: "
            + ConsoleColors.WHITE + concertName
            + ConsoleColors.RESET
        );

        System.out.println(
            ConsoleColors.YELLOW + "Seat Number: "
            + ConsoleColors.WHITE + seat.getSeatNumber()
            + ConsoleColors.RESET
        );

        System.out.println(
            ConsoleColors.YELLOW + "Ticket Category: "
            + ConsoleColors.WHITE + seat.getCategory()
            + ConsoleColors.RESET
        );

        System.out.printf(
            ConsoleColors.YELLOW + "Ticket Price: "
            + ConsoleColors.GREEN + "PHP %,.2f%n"
            + ConsoleColors.RESET,
            ticket.getPrice()
        );

        System.out.println(
            ConsoleColors.YELLOW + "Date Issued: "
            + ConsoleColors.WHITE + ticket.getIssueDate()
            + ConsoleColors.RESET
        );

        System.out.println(
            ConsoleColors.CYAN +
            "======================================"
            + ConsoleColors.RESET
        );

        System.out.println(
            ConsoleColors.GREEN +
            "       Thank you for choosing StagePass!"
            + ConsoleColors.RESET
        );
    }
}