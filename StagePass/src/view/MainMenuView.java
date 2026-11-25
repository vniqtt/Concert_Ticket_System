
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
                        + ConsoleColors.RESET);

        System.out.println(
                ConsoleColors.YELLOW + "1. View Concert"
                        + ConsoleColors.RESET);

        System.out.println(
                ConsoleColors.WHITE + "2. View Available Seats"
                        + ConsoleColors.RESET);

        System.out.println(
                ConsoleColors.WHITE + "3. Reserve a Seat"
                        + ConsoleColors.RESET);

        System.out.println(
                ConsoleColors.RED + "4. Exit"
                        + ConsoleColors.RESET);

        System.out.print(
                ConsoleColors.CYAN + "\nEnter your choice: "
                        + ConsoleColors.RESET);
    }

    public String getMenuInput() {
        return scanner.nextLine().trim();
    }

    public void displayAdminMenu() {

        System.out.println(
                ConsoleColors.PURPLE
                        + "\n===== STAGEPASS ADMIN MENU ====="
                        + ConsoleColors.RESET);

        System.out.println(
                ConsoleColors.CYAN
                        + "1. View All Purchases"
                        + ConsoleColors.RESET);

        System.out.println(
                ConsoleColors.YELLOW
                        + "2. Edit Purchase"
                        + ConsoleColors.RESET);

        System.out.println(
                ConsoleColors.RED
                        + "3. Delete Purchase"
                        + ConsoleColors.RESET);

        System.out.println(
                ConsoleColors.GREEN
                        + "4. Update Seat Status"
                        + ConsoleColors.RESET);

        System.out.println(
                ConsoleColors.WHITE
                        + "5. Return to Main Menu"
                        + ConsoleColors.RESET);

        System.out.print(
                ConsoleColors.PURPLE
                        + "\nEnter your choice: "
                        + ConsoleColors.RESET);
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