package com.academy.TransDana.service.impl;

import com.academy.TransDana.dto.CityDto;
import com.academy.TransDana.model.mapping.CityMapper;
import com.academy.TransDana.model.repository.CityRepository;
import com.academy.TransDana.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServerImpl implements CityService {
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public List<CityDto> getCities() {
        return cityMapper.map(cityRepository.findAll());
    }

}
