package repository;

import model.Ticket;
import java.util.ArrayList;
import java.util.List;

public class TicketRepository {

    private List<Ticket> tickets;

    public TicketRepository() {
        tickets = new ArrayList<>();
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public Ticket findTicketById(String ticketId) {
        for (Ticket ticket : tickets) {
            if (ticket.getTicketId().equalsIgnoreCase(ticketId)) {
                return ticket;
            }
        }

        return null;
    }

    public boolean ticketExists(String ticketId) {
        return findTicketById(ticketId) != null;
    }

    public List<Ticket> getAllTickets() {
        return tickets;
    }
}