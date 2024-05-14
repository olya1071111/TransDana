package com.academy.TransDana.service;

import com.academy.TransDana.dto.CityDto;
import com.academy.TransDana.model.domain.Car;
import com.academy.TransDana.model.domain.City;

import java.util.List;

public interface CityService {
    List<CityDto> getCities();
}
