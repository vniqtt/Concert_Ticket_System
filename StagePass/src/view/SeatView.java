package view;

import model.Seat;
import model.SeatStatus;
import java.util.List;

public class SeatView {

    public void displaySeats(List<Seat> seats) {

        System.out.println("\n===== CONCERT SEATING =====");

        for (Seat seat : seats) {

            String status;

            if (seat.getStatus() == SeatStatus.AVAILABLE) {
                status = "AVAILABLE";
            } else if (seat.getStatus() == SeatStatus.RESERVED) {
                status = "RESERVED";
            } else {
                status = "SOLD";
            }

            System.out.println(
                seat.getSeatNumber() +
                " | " + seat.getCategory() +
                " | PHP " + seat.getPrice() +
                " | " + status
            );
        }
    }
}