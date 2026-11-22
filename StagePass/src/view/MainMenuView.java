
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
                ConsoleColors.RED + "4. Exit"
                + ConsoleColors.RESET
        );

        System.out.print(
                ConsoleColors.CYAN + "\nEnter your choice: "
                + ConsoleColors.RESET
        );
    }

    public String getMenuInput() {
        return scanner.nextLine().trim();
    }

    public void displayAdminMenu() {

        System.out.println(
                ConsoleColors.PURPLE + "\n===== ADMIN MENU ====="
                + ConsoleColors.RESET
        );

        System.out.println("1. View All Purchases");
        System.out.println("2. Return to Main Menu");

        System.out.print("\nEnter your choice: ");
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