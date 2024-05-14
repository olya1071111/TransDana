package com.academy.TransDana.service;

import com.academy.TransDana.dto.CarDto;

import java.util.List;

public interface CarService {
    List<CarDto> getCars();

    void deleteCarById(Integer id);

    void save(CarDto car);

    public CarDto getById(Integer id);
}
