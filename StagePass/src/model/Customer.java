package model;

public class Customer {

    private String customerId;
    private String fullName;
    private String email;
    private String contactNumber;

    public Customer(String customerId, String fullName, String email, String contactNumber) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.email = email;
        this.contactNumber = contactNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getContactNumber() {
        return contactNumber;
    }
}