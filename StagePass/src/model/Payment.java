package model;

import java.time.LocalDateTime;

public abstract class Payment {

    private String paymentId;
    private double amount;
    private LocalDateTime paymentDate;
    private boolean successful;

    public Payment(String paymentId, double amount) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.paymentDate = LocalDateTime.now();
        this.successful = false;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public abstract boolean processPayment();
}