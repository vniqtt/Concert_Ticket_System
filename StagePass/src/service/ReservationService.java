package service;

import model.Customer;
import model.Reservation;
import model.Seat;
import model.SeatStatus;
import repository.ReservationRepository;
import repository.SeatRepository;

import java.util.UUID;

public class ReservationService {

    private ReservationRepository reservationRepository;
    private SeatRepository seatRepository;

    public ReservationService(
            ReservationRepository reservationRepository,
            SeatRepository seatRepository) {

        this.reservationRepository = reservationRepository;
        this.seatRepository = seatRepository;
    }

    public Reservation createReservation(Customer customer, String seatNumber) {

        Seat seat = seatRepository.findSeatByNumber(seatNumber);

        if (seat == null || seat.getStatus() != SeatStatus.AVAILABLE) {
            return null;
        }

        seat.setStatus(SeatStatus.RESERVED);

        String reservationId = UUID.randomUUID().toString();

        Reservation reservation = new Reservation(
                reservationId,
                customer,
                seat
        );

        reservationRepository.addReservation(reservation);

        return reservation;
    }

    public boolean cancelReservation(String reservationId) {

        Reservation reservation =
                reservationRepository.findReservationById(reservationId);

        if (reservation == null) {
            return false;
        }

        Seat seat = reservation.getSeat();

        if (seat.getStatus() != SeatStatus.RESERVED) {
            return false;
        }

        seat.setStatus(SeatStatus.AVAILABLE);

        return true;
    }
}