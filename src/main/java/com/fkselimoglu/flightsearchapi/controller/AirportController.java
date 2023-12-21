package com.fkselimoglu.flightsearchapi.controller;

import com.fkselimoglu.flightsearchapi.entity.Airport;
import com.fkselimoglu.flightsearchapi.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airports")
public class AirportController {
    @Autowired
    private AirportRepository airportRepository;

    @GetMapping
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    @GetMapping("/{id}")
    public Airport getAirportById(@PathVariable Long id) {
        return airportRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Airport createAirport(@RequestBody Airport airport) {
        return airportRepository.save(airport);
    }

    @PutMapping("/{id}")
    public void updateAirport(@PathVariable Long id, @RequestBody Airport airportDetails) {
        Airport airport = airportRepository.findById(id).orElse(null);
        if (airport != null) {
            airport.setCity(airportDetails.getCity());

            airportRepository.save(airport);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteAirport(@PathVariable Long id) {
        airportRepository.deleteById(id);
    }

    @GetMapping("/byCity")
    public List<Airport> getAirportsByCity(@RequestParam String city) {
        return airportRepository.findByCity(city);
    }
}
