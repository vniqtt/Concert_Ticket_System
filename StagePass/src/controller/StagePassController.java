
package controller;

import model.Concert;
import model.Seat;
import repository.SeatRepository;
import service.SeatInitializationService;
import view.MainMenuView;
import view.ConcertView;
import view.SeatView;

import java.util.Scanner;

public class StagePassController {

    private Scanner scanner;

    private Concert concert;

    private SeatRepository seatRepository;

    private MainMenuView mainMenuView;
    private ConcertView concertView;
    private SeatView seatView;

    public StagePassController(Concert concert) {

        this.scanner = new Scanner(System.in);
        this.concert = concert;

        this.seatRepository = new SeatRepository();

        this.mainMenuView = new MainMenuView(scanner);
        this.concertView = new ConcertView();
        this.seatView = new SeatView();

        initializeSeats();
    }

    private void initializeSeats() {

        SeatInitializationService initializer =
                new SeatInitializationService();

        initializer.initializeSeats(concert);

        for (Seat seat : concert.getSeats()) {
            seatRepository.addSeat(seat);
        }
    }

    public void start() {

        boolean running = true;

        while (running) {

            mainMenuView.displayMenu();

            int choice = mainMenuView.getMenuChoice();

            // Consume the remaining newline
            scanner.nextLine();

            switch (choice) {

                case 1:
                    concertView.displayConcertDetails(concert);
                    break;

                case 2:
                    seatView.displaySeats(
                            seatRepository.getAllSeats()
                    );
                    break;

                case 3:
                    System.out.println(
                            "Seat reservation will be implemented next."
                    );
                    break;

                case 4:
                    System.out.println(
                            "Ticket viewing will be implemented next."
                    );
                    break;

                case 5:
                    running = false;
                    System.out.println(
                            "\nThank you for using StagePass!"
                    );
                    break;

                default:
                    System.out.println(
                            "\nInvalid choice. Please try again."
                    );
            }
        }
    }
}