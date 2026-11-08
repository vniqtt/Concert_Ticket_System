package model;

import java.time.LocalDateTime;

public class Reservation {

    private String reservationId;
    private Customer customer;
    private Seat seat;
    private LocalDateTime reservationDate;
    private Payment payment;

    public Reservation(String reservationId, Customer customer, Seat seat) {
        this.reservationId = reservationId;
        this.customer = customer;
        this.seat = seat;
        this.reservationDate = LocalDateTime.now();
        this.payment = null;
    }

    public String getReservationId() {
        return reservationId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Seat getSeat() {
        return seat;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}