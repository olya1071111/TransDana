package com.academy.TransDana.model.mapping;

import com.academy.TransDana.dto.CarDto;
import com.academy.TransDana.model.domain.Car;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {

    CarDto toDTO(Car car);

    Car toEntity(CarDto carDto);

    List<CarDto> map(List<Car> cars);
}

