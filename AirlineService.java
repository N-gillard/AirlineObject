package com.example.FlightTrackerApp.Service;

import com.example.flighttracker.model.Airline;
import com.example.flighttracker.repository.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirlineService {

    private final AirlineRepository airlineRepository;

    @Autowired
    public AirlineService(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    public List<Airline> getAllAirlines() {
        return airlineRepository.findAll();
    }

    public Airline getAirlineById(Long id) {
        Optional<Airline> optionalAirline = airlineRepository.findById(id);
        return optionalAirline.orElse(null);
    }

    public void addAirline(Airline airline) {
        airlineRepository.save(airline);
    }

    public boolean updateAirline(Long id, Airline updatedAirline) {
        if (airlineRepository.existsById(id)) {
            updatedAirline.setAirlineId(id);
            airlineRepository.save(updatedAirline);
            return true;
        }
        return false;
    }

    public boolean deleteAirline(Long id) {
        if (airlineRepository.existsById(id)) {
            airlineRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
