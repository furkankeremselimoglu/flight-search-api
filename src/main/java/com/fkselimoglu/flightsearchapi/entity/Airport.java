package com.fkselimoglu.flightsearchapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String city;
}
