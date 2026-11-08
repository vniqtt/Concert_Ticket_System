package service;

import model.Concert;
import model.Seat;
import model.TicketCategory;

public class SeatInitializationService {

    public void initializeSeats(Concert concert) {

        // Front row
        for (int i = 1; i <= 10; i++) {
            concert.addSeat(
                new Seat("A" + i, TicketCategory.DIAMOND)
            );
        }

        // Second row
        for (int i = 1; i <= 10; i++) {
            concert.addSeat(
                new Seat("B" + i, TicketCategory.VIP)
            );
        }

        // Middle rows
        for (int i = 1; i <= 10; i++) {
            concert.addSeat(
                new Seat("C" + i, TicketCategory.PLATINUM)
            );
        }

        for (int i = 1; i <= 10; i++) {
            concert.addSeat(
                new Seat("D" + i, TicketCategory.GOLD)
            );
        }

        // Back row
        for (int i = 1; i <= 10; i++) {
            concert.addSeat(
                new Seat("E" + i, TicketCategory.SILVER)
            );
        }
    }
}