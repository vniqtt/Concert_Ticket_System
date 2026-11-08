
package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Concert {

    private String concertName;
    private LocalDate concertDate;
    private String venue;
    private List<Seat> seats;

    public Concert(String concertName, LocalDate concertDate, String venue) {
        this.concertName = concertName;
        this.concertDate = concertDate;
        this.venue = venue;
        this.seats = new ArrayList<>();
    }

    public String getConcertName() {
        return concertName;
    }

    public LocalDate getConcertDate() {
        return concertDate;
    }

    public String getVenue() {
        return venue;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void addSeat(Seat seat) {
        seats.add(seat);
    }
}
