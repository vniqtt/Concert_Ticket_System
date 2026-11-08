package service;

import model.Reservation;
import model.SeatStatus;
import model.Ticket;
import repository.TicketRepository;

import java.util.UUID;

public class TicketService {

    private TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket generateTicket(Reservation reservation) {

        if (reservation == null || reservation.getPayment() == null) {
            return null;
        }

        if (!reservation.getPayment().isSuccessful()) {
            return null;
        }

        if (reservation.getSeat().getStatus() != SeatStatus.SOLD) {
            return null;
        }

        String ticketId = UUID.randomUUID().toString();

        Ticket ticket = new Ticket(ticketId, reservation);

        ticketRepository.addTicket(ticket);

        return ticket;
    }

    public Ticket findTicketById(String ticketId) {
        return ticketRepository.findTicketById(ticketId);
    }
}