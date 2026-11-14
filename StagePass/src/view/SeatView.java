package view;

import model.Seat;
import model.SeatStatus;
import java.util.List;

public class SeatView {

    public void displaySeats(List<Seat> seats) {

        System.out.println(
            ConsoleColors.CYAN + "\n===== CONCERT SEATING ====="
            + ConsoleColors.RESET
        );

        for (Seat seat : seats) {

            String status;
            String statusColor;

            if (seat.getStatus() == SeatStatus.AVAILABLE) {
                status = "AVAILABLE";
                statusColor = ConsoleColors.GREEN;

            } else if (seat.getStatus() == SeatStatus.RESERVED) {
                status = "RESERVED";
                statusColor = ConsoleColors.YELLOW;

            } else {
                status = "SOLD";
                statusColor = ConsoleColors.RED;
            }

            System.out.println(
                ConsoleColors.WHITE +
                seat.getSeatNumber() +
                " | " + seat.getCategory() +
                " | PHP " + seat.getPrice() +
                " | " +
                statusColor + status +
                ConsoleColors.RESET
            );
        }
    }
}