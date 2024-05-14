package com.academy.TransDana.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Data
@Entity
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer number;
    /** This is "org.springframework.format.annotation.DateTimeFormat"*/
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date arrivaldate;
    /** This is "org.springframework.format.annotation.DateTimeFormat"*/
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date departuredate;
    private String arrival_address;
    private String departure_address;
    private String status;

    @ManyToOne()
    @JoinColumn(name = "car_id")
    private Car car;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne()
    @JoinColumn(name = "arrival_city_id")
    private City cityArrival;
    @ManyToOne()
    @JoinColumn(name = "departure_city_id")
    private City cityDeparture;
}
