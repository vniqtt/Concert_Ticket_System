package view;

import model.Concert;

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

        System.out.println(ConsoleColors.PURPLE + "Diamond: PHP 25,000" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.YELLOW + "VIP: PHP 20,000" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Platinum: PHP 15,000" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.GREEN + "Gold: PHP 9,000" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.WHITE + "Silver: PHP 4,000" + ConsoleColors.RESET);
    }
}