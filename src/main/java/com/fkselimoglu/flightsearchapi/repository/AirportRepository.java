package com.fkselimoglu.flightsearchapi.repository;

import com.fkselimoglu.flightsearchapi.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {

}
