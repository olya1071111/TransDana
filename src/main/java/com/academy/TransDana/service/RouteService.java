package com.academy.TransDana.service;

import com.academy.TransDana.dto.RouteDto;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

public interface RouteService {
    List<RouteDto> getAllRoutes();

    RouteDto getById(Integer id);

    Page<RouteDto> getAllRoutesByArrivaldateBetween(Date arrivalDate, Date departureDate, int page, int size);

    List<RouteDto> getAllRoutesByUser(Integer id);

    Page<RouteDto> getAllRoutesByUserAndArrivalDateBetween(Date arrivalDate, Date departureDate, int page, int size);

    void deleteRouteById(Integer id);

    void updateStatusRoute(Integer id, String status);

    void save(RouteDto route);
}
