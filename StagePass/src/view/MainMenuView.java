package view;

import java.util.Scanner;

public class MainMenuView {

    private Scanner scanner;

    public MainMenuView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayMenu() {

        System.out.println("\n===== STAGEPASS =====");
        System.out.println("1. View Concert");
        System.out.println("2. View Available Seats");
        System.out.println("3. Reserve a Seat");
        System.out.println("4. View My Ticket");
        System.out.println("5. Exit");

        System.out.print("\nEnter your choice: ");
    }

    public int getMenuChoice() {
        return scanner.nextInt();
    }
}