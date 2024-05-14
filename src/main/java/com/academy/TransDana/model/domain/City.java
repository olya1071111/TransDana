package com.academy.TransDana.model.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany
    @JoinColumn(name = "arrival_city_id")
    private List<Route> routesArrival;
    @OneToMany
    @JoinColumn(name = "departure_city_id")
    private List<Route> routesDeparture;
}
