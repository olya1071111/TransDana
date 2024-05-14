package com.academy.TransDana.service.impl;

import com.academy.TransDana.dto.RouteDto;
import com.academy.TransDana.model.domain.Login;
import com.academy.TransDana.model.domain.Route;
import com.academy.TransDana.model.mapping.RouteMapper;
import com.academy.TransDana.model.repository.RouteRepository;
import com.academy.TransDana.service.RouteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final RouteMapper routeMapper;
    public final static String STATUS_OPEN = "OPEN";
    private final static String STATUS_CLOSE = "CLOSE";

    @Override
    public List<RouteDto> getAllRoutes() {
        return routeMapper.map(routeRepository.findAll());
    }

    @Override
    public RouteDto getById(Integer id) {
        return routeMapper.toDTO(routeRepository.findById(id).get());
    }

    @Override
    public List<RouteDto> getAllRoutesByUser(Integer id) {
        return routeMapper.map(routeRepository.findAllByUser_id(id));
    }

    /**
     * The method getAllRoutesByArrivalDateBetween() is a service method for the controller
     * OperatorController.java that returns all routes in a given date range.
     * The initial date range is set to 1 month.
     */
    @Override
    public Page<RouteDto> getAllRoutesByArrivaldateBetween(Date arrivalDate, Date departureDate, int page, int size) {

        if (arrivalDate == null || departureDate == null) {
            arrivalDate = new Date();
            departureDate = DateUtils.addMonths(new Date(), 1);

        } else {
            departureDate = DateUtils.addDays(departureDate, 1);
        }

        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("arrivaldate"));
        Page<Route> routes = routeRepository.findByArrivaldateBetween(arrivalDate, departureDate, pageable);
        Page<RouteDto> routesDto = routes.map(routeMapper::toDTO);
        return routesDto;
    }


    /**
     * The method getAllRoutesByUserAndArrivalDateBetween() is a service method for the controller
     * UserController.java that returns all routes for each driver in a given date range.
     * The initial date range is set to 1 month.
     */

    public Page<RouteDto> getAllRoutesByUserAndArrivalDateBetween(Date arrivalDate,
                                                                  Date departureDate, int page, int size) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Login login = (Login) authentication.getPrincipal();

        if (arrivalDate == null || departureDate == null) {
            arrivalDate = new Date();
            departureDate = DateUtils.addMonths(new Date(), 1);
        } else {
            departureDate = DateUtils.addDays(departureDate, 1);
        }

        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("arrivaldate"));
        Page<Route> routes = routeRepository.findByUser_idAndArrivaldateBetween(login.getUser().getId(), arrivalDate, departureDate, pageable);
        Page<RouteDto> routesDto = routes.map(routeMapper::toDTO);

        return routesDto;
    }

    /**
     * The method deleteRouteById() is a service method for the controllers:
     * OperatorController and AdminController.
     * This service is used to delete the selected route
     */

    @Override
    @Transactional
    public void deleteRouteById(Integer id) {

        routeRepository.deleteById(id);
    }

    /**
     * The method updateStatusRoute() is a service method for the all controllers:
     * UserController.java, OperatorController and AdminController.
     * This service is used to change the status of the selected route
     */

    @Override
    @Transactional
    public void updateStatusRoute(Integer id, String status) {

        String tempStatus = "";

        if (status.equals(STATUS_CLOSE)) {
            tempStatus = STATUS_OPEN;
        } else if (status.equals(STATUS_OPEN)) {
            tempStatus = STATUS_CLOSE;
        }

        routeRepository.updateRouteStatus(id, tempStatus);
    }

    /**
     * The method save() is a service method for the all controllers:
     * UserController.java, OperatorController and AdminController.
     * This service is used for save the new route.
     */

    @Override
    @Transactional
    public void save(RouteDto route) {
        routeRepository.save(routeMapper.toEntity(route));
    }
}
