package com.academy.TransDana.service.impl;

import com.academy.TransDana.dto.CarDto;
import com.academy.TransDana.model.mapping.CarMapper;
import com.academy.TransDana.model.repository.CarRepository;
import com.academy.TransDana.service.CarService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Override
    public List<CarDto> getCars() {
        return carMapper.map(carRepository.findAll());
    }

    public void deleteCarById(Integer id) {

        carRepository.deleteById(id);
    }

    @Transactional
    public void save(CarDto car) {
        carRepository.save(carMapper.toEntity(car));
    }

    public CarDto getById(Integer id) {
        return carMapper.toDTO(carRepository.findById(id).get());
    }

}
