package service;

import model.Seat;
import model.SeatStatus;
import repository.SeatRepository;

public class SeatService {

    private SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public boolean reserveSeat(String seatNumber) {

        Seat seat = seatRepository.findSeatByNumber(seatNumber);

        if (seat == null) {
            return false;
        }

        if (seat.getStatus() != SeatStatus.AVAILABLE) {
            return false;
        }

        seat.setStatus(SeatStatus.RESERVED);

        return true;
    }

    public void markSeatAsSold(String seatNumber) {

        Seat seat = seatRepository.findSeatByNumber(seatNumber);

        if (seat != null && seat.getStatus() == SeatStatus.RESERVED) {
            seat.setStatus(SeatStatus.SOLD);
        }
    }

    public void cancelReservation(String seatNumber) {

        Seat seat = seatRepository.findSeatByNumber(seatNumber);

        if (seat != null && seat.getStatus() == SeatStatus.RESERVED) {
            seat.setStatus(SeatStatus.AVAILABLE);
        }
    }
}