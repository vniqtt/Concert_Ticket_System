package model;

public class CardPayment extends Payment {

    private String cardHolderName;

    public CardPayment(String paymentId, double amount, String cardHolderName) {
        super(paymentId, amount);
        this.cardHolderName = cardHolderName;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    @Override
    public boolean processPayment() {
        // Simulate a successful card payment
        setSuccessful(true);
        return true;
    }
}