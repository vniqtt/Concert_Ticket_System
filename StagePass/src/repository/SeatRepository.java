package repository;

import model.Seat;
import java.util.ArrayList;
import java.util.List;

public class SeatRepository {

    private List<Seat> seats;

    public SeatRepository() {
        seats = new ArrayList<>();
    }

    public void addSeat(Seat seat) {
        seats.add(seat);
    }

    public Seat findSeatByNumber(String seatNumber) {
        for (Seat seat : seats) {
            if (seat.getSeatNumber().equalsIgnoreCase(seatNumber)) {
                return seat;
            }
        }

        return null;
    }

    public boolean isSeatAvailable(String seatNumber) {
        Seat seat = findSeatByNumber(seatNumber);

        return seat != null &&
               seat.getStatus() == model.SeatStatus.AVAILABLE;
    }

    public List<Seat> getAllSeats() {
        return seats;
    }
}
