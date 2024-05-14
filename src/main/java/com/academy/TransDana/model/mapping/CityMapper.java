package com.academy.TransDana.model.mapping;

import com.academy.TransDana.dto.CityDto;
import com.academy.TransDana.model.domain.City;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper {
    CityDto toDTO(City city);

    City toEntity(CityDto cityDto);

    List<CityDto> map(List<City> cities);
}
