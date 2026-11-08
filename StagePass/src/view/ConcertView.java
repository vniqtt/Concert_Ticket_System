package view;

import model.Concert;

public class ConcertView {

    public void displayConcertDetails(Concert concert) {

        System.out.println("\n===== CONCERT DETAILS =====");

        System.out.println("Concert Name: " + concert.getConcertName());
        System.out.println("Date: " + concert.getConcertDate());
        System.out.println("Venue: " + concert.getVenue());

        System.out.println("\n===== TICKET PRICES =====");

        System.out.println("Diamond: PHP 25,000");
        System.out.println("VIP: PHP 20,000");
        System.out.println("Platinum: PHP 15,000");
        System.out.println("Gold: PHP 9,000");
        System.out.println("Silver: PHP 4,000");
    }
}