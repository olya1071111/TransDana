package com.academy.TransDana.model.repository;

import com.academy.TransDana.model.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {
}
