package repository;

import model.Payment;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepository {

    private List<Payment> payments;

    public PaymentRepository() {
        payments = new ArrayList<>();
    }

    public void addPayment(Payment payment) {
        payments.add(payment);
    }

    public Payment findPaymentById(String paymentId) {
        for (Payment payment : payments) {
            if (payment.getPaymentId().equalsIgnoreCase(paymentId)) {
                return payment;
            }
        }

        return null;
    }

    public boolean paymentExists(String paymentId) {
        return findPaymentById(paymentId) != null;
    }

    public List<Payment> getAllPayments() {
        return payments;
    }
}