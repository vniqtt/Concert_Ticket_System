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
    public void setFullName(String fullName) {
    this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}