package view;

import model.Customer;
import java.util.Scanner;
import java.util.UUID;

public class CustomerView {

    private Scanner scanner;

    public CustomerView(Scanner scanner) {
        this.scanner = scanner;
    }

    public Customer getCustomerDetails() {

        System.out.println("\n===== CUSTOMER INFORMATION =====");

        System.out.print("Enter full name: ");
        String fullName = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter contact number: ");
        String contactNumber = scanner.nextLine();

        String customerId = UUID.randomUUID().toString();

        return new Customer(
            customerId,
            fullName,
            email,
            contactNumber
        );
    }
}