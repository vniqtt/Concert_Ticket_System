
package view;

import model.Seat;
import model.SeatStatus;
import model.TicketCategory;
import java.util.List;

public class SeatView {

    // SHORT DISPLAY: Groups seats by category
    public void displaySeatCategories(List<Seat> seats) {

        System.out.println(
            ConsoleColors.CYAN + "\n===== CONCERT SEATING ====="
            + ConsoleColors.RESET
        );

        for (TicketCategory category : TicketCategory.values()) {

            String firstSeat = null;
            String lastSeat = null;

            for (Seat seat : seats) {

                if (seat.getCategory() == category) {

                    if (firstSeat == null) {
                        firstSeat = seat.getSeatNumber();
                    }

                    lastSeat = seat.getSeatNumber();
                }
            }

            if (firstSeat != null) {

                System.out.println(
                    ConsoleColors.WHITE
                    + category + ": "
                    + firstSeat + "-" + lastSeat
                    + ConsoleColors.RESET
                );
            }
        }
    }

    // DETAILED DISPLAY: Shows individual seats and their statuses
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
                ConsoleColors.WHITE
                + seat.getSeatNumber()
                + " | " + seat.getCategory()
                + " | PHP " + String.format("%,d", seat.getPrice())
                + " | "
                + statusColor + status
                + ConsoleColors.RESET
            );
        }
    }
}
