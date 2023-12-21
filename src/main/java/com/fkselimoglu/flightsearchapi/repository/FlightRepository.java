package com.fkselimoglu.flightsearchapi.repository;

import com.fkselimoglu.flightsearchapi.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {

}
