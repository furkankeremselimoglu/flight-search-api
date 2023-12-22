package com.fkselimoglu.flightsearchapi.controller;

import com.fkselimoglu.flightsearchapi.entity.Flight;
import com.fkselimoglu.flightsearchapi.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    private FlightRepository flightRepository;

    @GetMapping
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFlightById(@PathVariable Long id) {
        Optional<Flight> flight = flightRepository.findById(id);
        if (flight.isPresent()) {
            return ResponseEntity.ok(flight.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Flight not found with id: " + id);
        }
    }

    @PostMapping
    public Flight createFlight(@RequestBody Flight flight) {
        return flightRepository.save(flight);
    }

    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable Long id) {
        flightRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public void updateFlight(@PathVariable Long id, @RequestBody Flight flightDetails) {
        Flight flight = flightRepository.findById(id).orElse(null);
        if (flight != null) {
            flight.setDepartureAirport(flightDetails.getDepartureAirport());
            flight.setArrivalAirport(flightDetails.getArrivalAirport());
            flight.setDepartureTime(flightDetails.getDepartureTime());
            flight.setArrivalTime(flightDetails.getArrivalTime());
            flight.setPrice(flightDetails.getPrice());

            flightRepository.save(flight);
        }
    }

    @GetMapping("/search")
    public List<Flight> searchFlightsByAirportsAndTime(
            @RequestParam String departureAirport,
            @RequestParam String arrivalAirport,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureTime) {
        return flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureTime(
                departureAirport, arrivalAirport, departureTime);
    }

    @GetMapping("/searchFlights")
    public ResponseEntity<?> searchFlights(
            @RequestParam String departureAirport,
            @RequestParam String arrivalAirport,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureTime,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime returnTime) {

        List<Flight> departureFlights = flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureTime(
                departureAirport, arrivalAirport, departureTime);

        if (returnTime == null) {
            // One direction flight
            return new ResponseEntity<>(departureFlights, HttpStatus.OK);
        } else {
            // Two direction flight
            List<Flight> returnFlights = flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureTime(
                    arrivalAirport, departureAirport, returnTime);
            return new ResponseEntity<>(new Object[]{departureFlights, returnFlights}, HttpStatus.OK);
        }
    }

    @GetMapping("/flightsByAirports")
    public List<Flight> getFlightsByDepartureAndArrivalAirports(
            @RequestParam String departureAirport,
            @RequestParam String arrivalAirport) {
        return flightRepository.findByDepartureAirportAndArrivalAirport(departureAirport, arrivalAirport);
    }

    @GetMapping("/flightsByDepartureAndArrivalTimes")
    public List<Flight> getFlightsByDepartureAndArrivalTimes(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime arrivalTime) {
        return flightRepository.findByDepartureTimeAfterAndArrivalTimeBefore(departureTime, arrivalTime);
    }
}
