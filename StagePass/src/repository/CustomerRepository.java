package repository;

import model.Customer;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {

    private List<Customer> customers;

    public CustomerRepository() {
        customers = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Customer findCustomerById(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equalsIgnoreCase(customerId)) {
                return customer;
            }
        }

        return null;
    }

    public boolean customerExists(String customerId) {
        return findCustomerById(customerId) != null;
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }
}