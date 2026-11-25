package view;

import model.Payment;
import model.CashPayment;
import model.CardPayment;
import model.GCashPayment;

import java.util.Scanner;
import java.util.UUID;

public class PaymentView {

    private Scanner scanner;

    public PaymentView(Scanner scanner) {
        this.scanner = scanner;
    }

    public Payment getPaymentDetails(double amount) {

        System.out.println(
        ConsoleColors.CYAN + "\n===== PAYMENT METHOD ====="
        + ConsoleColors.RESET
    );

    System.out.println(
        ConsoleColors.YELLOW + "Amount to Pay: PHP " + amount
        + ConsoleColors.RESET
    );

    System.out.println(
        ConsoleColors.GREEN + "\n1. Cash"
        + ConsoleColors.RESET
    );

    System.out.println(
        ConsoleColors.BLUE + "2. Card"
        + ConsoleColors.RESET
    );

    System.out.println(
        ConsoleColors.PURPLE + "3. GCash"
        + ConsoleColors.RESET
    );

    int choice;

    // Validate payment method input
    while (true) {

        System.out.print(
        ConsoleColors.CYAN + "\nChoose payment method: "
        + ConsoleColors.RESET
    );

    String input = scanner.nextLine().trim();

    try {
        choice = Integer.parseInt(input);

        if (choice >= 1 && choice <= 3) {
            break;
        }

        System.out.println(
            ConsoleColors.RED + "Invalid payment method! Please choose 1, 2, or 3."
            + ConsoleColors.RESET
        );

    } catch (NumberFormatException e) {

        System.out.println(
            ConsoleColors.RED + "Invalid input! Please enter numbers only."
            + ConsoleColors.RESET
        );
    }

    // Wait briefly, then clear the console
    try {
        Thread.sleep(1000);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }

    

    // Display the payment menu again
    displayPaymentMenu(amount);
    }

    String paymentId = UUID.randomUUID().toString();

    switch (choice) {

        case 1:
            return new CashPayment(paymentId, amount);

        case 2:
            System.out.print(
                ConsoleColors.YELLOW + "Enter cardholder name: "
                + ConsoleColors.RESET
            );

            String cardHolderName = scanner.nextLine();

            return new CardPayment(
                paymentId,
                amount,
                cardHolderName
            );

        case 3:
            System.out.print(
                ConsoleColors.YELLOW + "Enter GCash account name: "
                + ConsoleColors.RESET
            );

            String accountName = scanner.nextLine();

            return new GCashPayment(
                paymentId,
                amount,
                accountName
            );

        default:
            return null;
        }
    }

    private void displayPaymentMenu(double amount) {

        System.out.println(
            ConsoleColors.CYAN + "\n===== PAYMENT METHOD ====="
            + ConsoleColors.RESET
        );

        System.out.println(
            ConsoleColors.YELLOW + "Amount to Pay: PHP " + amount
            + ConsoleColors.RESET
        );

        System.out.println(ConsoleColors.GREEN + "\n1. Cash" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "2. Card" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.PURPLE + "3. GCash" + ConsoleColors.RESET);
    }

    public void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls")
                .inheritIO()
                .start()
                .waitFor();
        } catch (Exception e) {
            System.out.println("Unable to clear the screen.");
        }
    }
}