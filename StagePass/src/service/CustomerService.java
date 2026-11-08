package service;

import model.Customer;
import repository.CustomerRepository;

public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public boolean registerCustomer(Customer customer) {

        if (customerRepository.customerExists(customer.getCustomerId())) {
            return false;
        }

        customerRepository.addCustomer(customer);

        return true;
    }

    public Customer findCustomerById(String customerId) {
        return customerRepository.findCustomerById(customerId);
    }
}