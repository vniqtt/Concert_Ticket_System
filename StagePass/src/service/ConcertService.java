package service;

import model.Concert;
import repository.ConcertRepository;

public class ConcertService {

    private ConcertRepository concertRepository;

    public ConcertService(ConcertRepository concertRepository) {
        this.concertRepository = concertRepository;
    }

    public boolean addConcert(Concert concert) {

        if (concertRepository.concertExists(concert.getConcertName())) {
            return false;
        }

        concertRepository.addConcert(concert);

        return true;
    }

    public Concert findConcertByName(String concertName) {
        return concertRepository.findConcertByName(concertName);
    }
}