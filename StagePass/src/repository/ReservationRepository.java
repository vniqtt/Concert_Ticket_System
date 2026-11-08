package repository;

import model.Reservation;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepository {

    private List<Reservation> reservations;

    public ReservationRepository() {
        reservations = new ArrayList<>();
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public Reservation findReservationById(String reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationId().equalsIgnoreCase(reservationId)) {
                return reservation;
            }
        }

        return null;
    }

    public boolean reservationExists(String reservationId) {
        return findReservationById(reservationId) != null;
    }

    public List<Reservation> getAllReservations() {
        return reservations;
    }
}