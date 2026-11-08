package service;

import model.Payment;
import model.Reservation;
import model.SeatStatus;
import repository.PaymentRepository;

public class PaymentService {

    private PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public boolean processPayment(Reservation reservation, Payment payment) {

        if (reservation == null || payment == null) {
            return false;
        }

        if (reservation.getSeat().getStatus() != SeatStatus.RESERVED) {
            return false;
        }

        if (payment.processPayment()) {

            reservation.setPayment(payment);

            reservation.getSeat().setStatus(SeatStatus.SOLD);

            paymentRepository.addPayment(payment);

            return true;
        }

        return false;
    }
}