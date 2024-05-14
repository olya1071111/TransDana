package com.academy.TransDana.controller;

import com.academy.TransDana.dto.CityDto;
import com.academy.TransDana.model.mapping.CarMapper;
import com.academy.TransDana.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;
    private final CarMapper carMapper;

    /**
     * The method getCities() returns a list of all cities
     */

    @GetMapping("/operator/city")
    public String getCities(Model model) {

        List<CityDto> cities = cityService.getCities();
        model.addAttribute("cities", cities);

        return "operator/cities";
    }
}
