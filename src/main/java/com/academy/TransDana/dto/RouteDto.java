package com.academy.TransDana.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteDto {
    private Integer id;
    @NotEmpty(message = "The field should not be empty")
    private Integer number;
    @NotEmpty(message = "The field should not be empty")
    private Date arrivaldate;
    @NotEmpty(message = "The field should not be empty")
    private Date departuredate;
    @NotEmpty(message = "The field should not be empty")
    private String arrival_address;
    @NotEmpty(message = "The field should not be empty")
    private String departure_address;
    @NotEmpty(message = "The field should not be empty")
    private String status;
    @NotEmpty(message = "The field should not be empty")
    private CarDto car;
    @NotEmpty(message = "The field should not be empty")
    private UserDto user;
    @NotEmpty(message = "The field should not be empty")
    private CityDto cityArrival;
    @NotEmpty(message = "The field should not be empty")
    private CityDto cityDeparture;
}
