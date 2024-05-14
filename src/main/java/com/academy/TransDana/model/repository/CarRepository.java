package com.academy.TransDana.model.repository;

import com.academy.TransDana.model.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Integer> {

}
