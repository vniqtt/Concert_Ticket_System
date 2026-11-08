package model;

public class GCashPayment extends Payment {

    private String accountName;

    public GCashPayment(String paymentId, double amount, String accountName) {
        super(paymentId, amount);
        this.accountName = accountName;
    }

    public String getAccountName() {
        return accountName;
    }

    @Override
    public boolean processPayment() {
        // Simulate a successful GCash payment
        setSuccessful(true);
        return true;
    }
}