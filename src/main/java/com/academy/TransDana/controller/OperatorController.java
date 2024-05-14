package com.academy.TransDana.controller;

import com.academy.TransDana.dto.RouteDto;
import com.academy.TransDana.model.domain.Route;
import com.academy.TransDana.model.mapping.RouteMapper;
import com.academy.TransDana.service.CarService;
import com.academy.TransDana.service.CityService;
import com.academy.TransDana.service.RouteService;
import com.academy.TransDana.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

import static com.academy.TransDana.config.SecurityConfig.ROLE_USER;
import static com.academy.TransDana.controller.UserController.MESSAGE_STATUS_CHANGE_SUCCESSFULLY;
import static com.academy.TransDana.controller.UserController.MESSAGE_STATUS_CHANGE_UNSUCCESSFULLY;
import static com.academy.TransDana.service.impl.RouteServiceImpl.STATUS_OPEN;

@Controller
@RequiredArgsConstructor
public class OperatorController {

    private final RouteService routeService;
    private final CityService cityService;
    private final CarService carService;
    private final UserService userService;
    private final RouteMapper routeMapper;
    private final static String MESSAGE_STATUS_DELETE_SUCCESSFULLY = "Delete the route № ";
    private final static String MESSAGE_STATUS_DELETE_UNSUCCESSFULLY = "Don't delete the route № ";
    private final static String MESSAGE_STATUS_SAVE_SUCCESSFULLY = "Save the route № ";
    private final static String MESSAGE_STATUS_SAVE_UNSUCCESSFULLY = "Don't save the route № ";


    /**
     * The method getRoute() returns a list of all routes with the specified date period.
     * It is necessary to set the parameters of the start date period and the end date of the route presentation period
     */

    @GetMapping("/operator/routes")
    public String getRoute(Model model,
                           @Param("arrivalDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date arrivalDate,
                           @Param("departureDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date departureDate,
                           @RequestParam(defaultValue = "1") int page,
                           @RequestParam(defaultValue = "5") int size) {

        try {

            Page<RouteDto> pageTuts = routeService.getAllRoutesByArrivaldateBetween(arrivalDate,
                    departureDate, page, size);

            model.addAttribute("routes", pageTuts.getContent());
            model.addAttribute("currentPage", pageTuts.getNumber() + 1);
            model.addAttribute("totalItems", pageTuts.getTotalElements());
            model.addAttribute("totalPages", pageTuts.getTotalPages());
            model.addAttribute("pageSize", size);
            model.addAttribute("arrivalDate", arrivalDate);
            model.addAttribute("departureDate", departureDate);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return "operator/routes";
    }

    /**
     * The method deleteRoute() deletes the current record from the database by id
     */

    @GetMapping("/operator/delete/{id}/{number}")
    public String deleteRoute(@PathVariable("id") Integer id, @PathVariable("number") Integer number,
                              Model model, RedirectAttributes redirectAttributes) {
        try {
            routeService.deleteRouteById(id);

            redirectAttributes.addFlashAttribute("message", MESSAGE_STATUS_DELETE_SUCCESSFULLY + number);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", MESSAGE_STATUS_DELETE_UNSUCCESSFULLY + number);
        }

        return "redirect:/operator/routes";

    }

    /**
     * The method editRoute() returns the current object from the view for later modification
     */

    @GetMapping("/operator/{id}")
    public String editRoute(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {

            model.addAttribute("route", routeService.getById(id));
            model.addAttribute("pageTitle", "Edit Route (ID: " + id + ")");
            model.addAttribute("arrival_city", cityService.getCities());
            model.addAttribute("departure_city", cityService.getCities());
            model.addAttribute("cars", carService.getCars());
            model.addAttribute("users", userService.getAll());

            return "/operator/route_form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/operator/routes";
        }
    }

    /**
     * The method addRoute() creates objects to add a route to the database
     */

    @GetMapping("/operator/route/new")
    public String addRoute(Model model) {

        model.addAttribute("route", new Route());
        model.addAttribute("arrival_city", cityService.getCities());
        model.addAttribute("departure_city", cityService.getCities());
        model.addAttribute("cars", carService.getCars());
        model.addAttribute("logins", userService.getAllLogin(ROLE_USER));
        model.addAttribute("pageTitle", "Create new Route:");

        return "/operator/route_form_add";

    }

    /**
     * The method saveRoute() adds a new route to the database from the returned view
     */

    @PostMapping("/operator/route/save")
    public String saveRoute(@Valid Route route, RedirectAttributes redirectAttributes) {

        route.setStatus(STATUS_OPEN);
        try {
            routeService.save(routeMapper.toDTO(route));
            redirectAttributes.addFlashAttribute("message", MESSAGE_STATUS_SAVE_SUCCESSFULLY + route.getNumber());

        } catch (Exception e) {
            redirectAttributes.addAttribute("message", MESSAGE_STATUS_SAVE_UNSUCCESSFULLY + route.getNumber());
        }

        return "redirect:/operator/routes";
    }

    /**
     * The method changeStatus() changes the current status of the route
     */

    @GetMapping("/operator/edit/{id}/{number}/{status}")
    public String changeStatus(@PathVariable("id") Integer id, @PathVariable("number") Integer number,
                               @PathVariable("status") String status, RedirectAttributes redirectAttributes) {

        try {

            routeService.updateStatusRoute(id, status);
            redirectAttributes.addFlashAttribute("message", MESSAGE_STATUS_CHANGE_SUCCESSFULLY + number);

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute("message", MESSAGE_STATUS_CHANGE_UNSUCCESSFULLY + number);
        }
        return "redirect:/operator/routes";
    }
}
