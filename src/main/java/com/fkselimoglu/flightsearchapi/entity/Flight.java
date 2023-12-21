package com.fkselimoglu.flightsearchapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String departureAirport;
    private String arrivalAirport;
    private LocalDateTime departureTİme;
    private LocalDateTime returnTime;
    private double price;
}
