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

        System.out.println(
            ConsoleColors.CYAN + "\n===== CUSTOMER INFORMATION ====="
            + ConsoleColors.RESET
        );

        System.out.print(
            ConsoleColors.YELLOW + "Enter full name: "
            + ConsoleColors.RESET
        );
        String fullName = scanner.nextLine();

        System.out.print(
            ConsoleColors.YELLOW + "Enter email: "
            + ConsoleColors.RESET
        );
        String email = scanner.nextLine();

        System.out.print(
            ConsoleColors.YELLOW + "Enter contact number: "
            + ConsoleColors.RESET
        );
        String contactNumber = scanner.nextLine();

        String customerId = UUID.randomUUID().toString();

        System.out.println(
            ConsoleColors.GREEN + "\nCustomer information entered successfully!"
            + ConsoleColors.RESET
        );

        return new Customer(
            customerId,
            fullName,
            email,
            contactNumber
        );
    }
}