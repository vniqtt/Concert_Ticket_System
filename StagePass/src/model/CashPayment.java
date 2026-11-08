package model;

public class CashPayment extends Payment {

    public CashPayment(String paymentId, double amount) {
        super(paymentId, amount);
    }

    @Override
    public boolean processPayment() {
        // Simulate a successful cash payment
        setSuccessful(true);
        return true;
    }
}