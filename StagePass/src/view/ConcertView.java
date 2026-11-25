package view;

import model.Concert;
import model.TicketCategory;

public class ConcertView {

    public void displayConcertDetails(Concert concert) {

        System.out.println(
            ConsoleColors.CYAN + "\n===== CONCERT DETAILS ====="
            + ConsoleColors.RESET
        );

        System.out.println(
            ConsoleColors.YELLOW + "Concert Name: "
            + ConsoleColors.WHITE + concert.getConcertName()
            + ConsoleColors.RESET
        );

        System.out.println(
            ConsoleColors.YELLOW + "Date: "
            + ConsoleColors.WHITE + concert.getConcertDate()
            + ConsoleColors.RESET
        );

        System.out.println(
            ConsoleColors.YELLOW + "Venue: "
            + ConsoleColors.WHITE + concert.getVenue()
            + ConsoleColors.RESET
        );

        System.out.println(
            ConsoleColors.CYAN + "\n===== TICKET PRICES ====="
            + ConsoleColors.RESET
        );

        System.out.println(
    ConsoleColors.PURPLE + "Diamond: PHP "
    + String.format("%,d", TicketCategory.DIAMOND.getPrice())
    + ConsoleColors.RESET
);

System.out.println(
    ConsoleColors.YELLOW + "VIP: PHP "
    + String.format("%,d", TicketCategory.VIP.getPrice())
    + ConsoleColors.RESET
);

System.out.println(
    ConsoleColors.BLUE + "Platinum: PHP "
    + String.format("%,d", TicketCategory.PLATINUM.getPrice())
    + ConsoleColors.RESET
);

System.out.println(
    ConsoleColors.GREEN + "Gold: PHP "
    + String.format("%,d", TicketCategory.GOLD.getPrice())
    + ConsoleColors.RESET
);

System.out.println(
    ConsoleColors.WHITE + "Silver: PHP "
    + String.format("%,d", TicketCategory.SILVER.getPrice())
    + ConsoleColors.RESET
);
    }
}