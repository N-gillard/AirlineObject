package com.example.FlightTrackerApp.Controller;

import com.example.flighttracker.model.Airline;
import com.example.flighttracker.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airlines")
public class AirlineController {

    private final AirlineService airlineService;

    @Autowired
    public AirlineController(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    // Endpoint to retrieve all airlines
    @GetMapping
    public ResponseEntity<List<Airline>> getAllAirlines() {
        List<Airline> airlines = airlineService.getAllAirlines();
        return new ResponseEntity<>(airlines, HttpStatus.OK);
    }

    // Endpoint to retrieve an airline by ID
    @GetMapping("/{id}")
    public ResponseEntity<Airline> getAirlineById(@PathVariable Long id) {
        Airline airline = airlineService.getAirlineById(id);
        if (airline != null) {
            return new ResponseEntity<>(airline, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to add a new airline
    @PostMapping
    public ResponseEntity<Void> addAirline(@RequestBody Airline airline) {
        airlineService.addAirline(airline);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Endpoint to update an existing airline
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAirline(@PathVariable Long id, @RequestBody Airline airline) {
        if (airlineService.updateAirline(id, airline)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to delete an existing airline
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirline(@PathVariable Long id) {
        if (airlineService.deleteAirline(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
