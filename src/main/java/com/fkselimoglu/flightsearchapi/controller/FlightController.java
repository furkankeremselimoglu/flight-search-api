package com.fkselimoglu.flightsearchapi.controller;

import com.fkselimoglu.flightsearchapi.entity.Flight;
import com.fkselimoglu.flightsearchapi.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    public Flight getFlightById(@PathVariable Long id) {
        return flightRepository.findById(id).orElse(null);
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
            flight.setPrice(flight.getPrice());

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
