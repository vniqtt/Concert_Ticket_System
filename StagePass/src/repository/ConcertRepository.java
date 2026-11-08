package repository;

import model.Concert;
import java.util.ArrayList;
import java.util.List;

public class ConcertRepository {

    private List<Concert> concerts;

    public ConcertRepository() {
        concerts = new ArrayList<>();
    }

    public void addConcert(Concert concert) {
        concerts.add(concert);
    }

    public Concert findConcertByName(String concertName) {
        for (Concert concert : concerts) {
            if (concert.getConcertName().equalsIgnoreCase(concertName)) {
                return concert;
            }
        }

        return null;
    }

    public boolean concertExists(String concertName) {
        return findConcertByName(concertName) != null;
    }

    public List<Concert> getAllConcerts() {
        return concerts;
    }
}