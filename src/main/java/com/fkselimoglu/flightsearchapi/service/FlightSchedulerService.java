package com.fkselimoglu.flightsearchapi.service;

import com.fkselimoglu.flightsearchapi.entity.Flight;
import com.fkselimoglu.flightsearchapi.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class FlightSchedulerService {
    @Autowired
    private FlightRepository flightRepository;

    @Scheduled(cron = "0 0 9 * * ?") // Scheduled to work for every day at 09:00
    public void updateFlightInformation() {
        List<Flight> flights = fetchMockFlightData();
        flightRepository.saveAll(flights);
    }

    private List<Flight> fetchMockFlightData() {
        List<Flight> mockFlights = new ArrayList<>();
        // Creating artificial data in a simple structure
        for (int i = 0; i < 5; i++) {
            Flight flight = new Flight();
            flight.setDepartureAirport("Airport " + i);
            flight.setArrivalAirport("Airport " + (i + 1));
            flight.setDepartureTime(LocalDateTime.now().plusDays(i));
            flight.setArrivalTime(LocalDateTime.now().plusDays(i).plusHours(2));
            flight.setPrice(new Random().nextDouble() * 100 + 100);
            mockFlights.add(flight);
        }
        return mockFlights;
    }
}
