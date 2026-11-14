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

        System.out.print(
            ConsoleColors.CYAN + "\nChoose payment method: "
            + ConsoleColors.RESET
        );

        int choice = scanner.nextInt();
        scanner.nextLine();

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
                System.out.println(
                    ConsoleColors.RED + "Invalid payment method!"
                    + ConsoleColors.RESET
                );

                return null;
        }
    }
}