package com.fkselimoglu.flightsearchapi.repository;

import com.fkselimoglu.flightsearchapi.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByDepartureTimeBetween(LocalDateTime start, LocalDateTime end);
    List<Flight> findByDepartureAirportAndArrivalAirport(String departureAirport, String arrivalAirport);
    List<Flight> findByDepartureTimeAfterAndArrivalTimeBefore(LocalDateTime departureTime, LocalDateTime arrivalTime);

}
