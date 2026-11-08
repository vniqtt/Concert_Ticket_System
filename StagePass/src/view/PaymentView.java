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

        System.out.println("\n===== PAYMENT METHOD =====");
        System.out.println("Amount to Pay: PHP " + amount);

        System.out.println("\n1. Cash");
        System.out.println("2. Card");
        System.out.println("3. GCash");

        System.out.print("\nChoose payment method: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        String paymentId = UUID.randomUUID().toString();

        switch (choice) {

            case 1:
                return new CashPayment(paymentId, amount);

            case 2:
                System.out.print("Enter cardholder name: ");
                String cardHolderName = scanner.nextLine();

                return new CardPayment(
                    paymentId,
                    amount,
                    cardHolderName
                );

            case 3:
                System.out.print("Enter GCash account name: ");
                String accountName = scanner.nextLine();

                return new GCashPayment(
                    paymentId,
                    amount,
                    accountName
                );

            default:
                System.out.println("Invalid payment method!");
                return null;
        }
    }
}