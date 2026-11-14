package view;

import java.util.Scanner;

public class MainMenuView {

    private Scanner scanner;

    public MainMenuView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayMenu() {

        System.out.println(
                ConsoleColors.CYAN + "\n===== STAGEPASS ====="
                + ConsoleColors.RESET
        );

        System.out.println(
                ConsoleColors.YELLOW + "1. View Concert"
                + ConsoleColors.RESET
        );

        System.out.println(
                ConsoleColors.WHITE + "2. View Available Seats"
                + ConsoleColors.RESET
        );

        System.out.println(
                ConsoleColors.WHITE + "3. Reserve a Seat"
                + ConsoleColors.RESET
        );

        System.out.println(
                ConsoleColors.WHITE + "4. View My Ticket"
                + ConsoleColors.RESET
        );

        System.out.println(
                ConsoleColors.RED + "5. Exit"
                + ConsoleColors.RESET
        );

        System.out.print(
                ConsoleColors.CYAN + "\nEnter your choice: "
                + ConsoleColors.RESET
        );
    }

    public int getMenuChoice() {
        String input = scanner.nextLine();

        try {
            return Integer.parseInt(input.trim());

        } catch (NumberFormatException e) {
            return -1;
        }
    }
}