package model;

import java.time.LocalDateTime;

public class Ticket {

    private String ticketId;
    private Reservation reservation;
    private LocalDateTime issueDate;

    public Ticket(String ticketId, Reservation reservation) {
        this.ticketId = ticketId;
        this.reservation = reservation;
        this.issueDate = LocalDateTime.now();
    }

    public String getTicketId() {
        return ticketId;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public double getPrice() {
        return reservation.getSeat().getPrice();
    }
}