
package model;

public class Seat {

    private String seatNumber;
    private TicketCategory category;
    private SeatStatus status;

    public Seat(String seatNumber, TicketCategory category) {
        this.seatNumber = seatNumber;
        this.category = category;
        this.status = SeatStatus.AVAILABLE;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public TicketCategory getCategory() {
        return category;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }

    public double getPrice() {
        return category.getPrice();
    }
}
