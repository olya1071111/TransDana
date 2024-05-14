package com.academy.TransDana.controller;

import com.academy.TransDana.dto.CarDto;
import com.academy.TransDana.dto.LoginDto;
import com.academy.TransDana.dto.RouteDto;
import com.academy.TransDana.dto.UserDto;
import com.academy.TransDana.model.domain.Car;
import com.academy.TransDana.model.domain.Login;
import com.academy.TransDana.model.domain.Route;
import com.academy.TransDana.model.mapping.CarMapper;
import com.academy.TransDana.model.mapping.RouteMapper;
import com.academy.TransDana.service.CarService;
import com.academy.TransDana.service.CityService;
import com.academy.TransDana.service.RouteService;
import com.academy.TransDana.service.UserService;
import com.academy.TransDana.util.LoginValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

import static com.academy.TransDana.config.SecurityConfig.ROLE_USER;
import static com.academy.TransDana.controller.CarController.*;
import static com.academy.TransDana.controller.UserController.MESSAGE_STATUS_CHANGE_SUCCESSFULLY;
import static com.academy.TransDana.controller.UserController.MESSAGE_STATUS_CHANGE_UNSUCCESSFULLY;
import static com.academy.TransDana.service.impl.RouteServiceImpl.STATUS_OPEN;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final RouteService routeService;
    private final CityService cityService;
    private final CarService carService;
    private final UserService userService;
    private final LoginValidator loginValidator;
    private final RouteMapper routeMapper;
    private final CarMapper carMapper;
    private final static String MESSAGE_STATUS_DELETE_SUCCESSFULLY = "Delete the route № ";
    private final static String MESSAGE_STATUS_DELETE_UNSUCCESSFULLY = "Don't delete the route № ";
    private final static String MESSAGE_STATUS_SAVE_SUCCESSFULLY = "Save the route № ";
    private final static String MESSAGE_STATUS_SAVE_UNSUCCESSFULLY = "Don't saved the route № ";
    private final String MESSAGE_USER_SAVE_SUCCESSFULLY = "Successfully saved user ";
    private final String MESSAGE_USER_SAVE_UNSUCCESSFULLY = "Don't saved user ";
    private final String MESSAGE_USER_DELETE_SUCCESSFULLY = "Successfully delete a user ";
    private final String MESSAGE_USER_DELETE_UNSUCCESSFULLY = "Don't delete a user ";
    private final String BLOCKED = "_BLOCKED";

    /**
     * The method getRoute() returns a list of all routes with the specified date period.
     * It is necessary to set the parameters of the start date period and the end date of the route presentation period
     */

    @GetMapping("/admin/routes")
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

        return "admin/routes";
    }

    /**
     * The method deleteRoute() deletes the current record from the database by id
     */

    @GetMapping("/admin/delete/{id}/{number}")
    public String deleteRoute(@PathVariable("id") Integer id, @PathVariable("number") Integer number,
                              RedirectAttributes redirectAttributes) {
        try {
            routeService.deleteRouteById(id);

            redirectAttributes.addFlashAttribute("message", MESSAGE_STATUS_DELETE_SUCCESSFULLY + number);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", MESSAGE_STATUS_DELETE_UNSUCCESSFULLY + number);
        }

        return "redirect:/admin/routes";

    }

    /**
     * The method editRoute() returns the current object from the view for later modification
     */

    @GetMapping("/admin/{id}")
    public String editRoute(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {

            model.addAttribute("route", routeService.getById(id));
            model.addAttribute("pageTitle", "Edit Route (ID: " + id + ")");
            model.addAttribute("arrival_city", cityService.getCities());
            model.addAttribute("departure_city", cityService.getCities());
            model.addAttribute("cars", carService.getCars());
            model.addAttribute("users", userService.getAll());

            return "/admin/route_form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/admin/routes";
        }
    }

    /**
     * The method addRoute() creates objects to add a route to the database
     */

    @GetMapping("/admin/route/new")
    public String addRoute(Model model) {

        model.addAttribute("route", new Route());
        model.addAttribute("arrival_city", cityService.getCities());
        model.addAttribute("departure_city", cityService.getCities());
        model.addAttribute("cars", carService.getCars());
        model.addAttribute("logins", userService.getAllLogin(ROLE_USER));
        model.addAttribute("pageTitle", "Create new Route:");

        return "/admin/route_form_add";

    }

    /**
     * The method saveRoute() adds a new route to the database from the returned view
     */

    @PostMapping("/admin/route/save")
    public String saveRoute(@Valid Route route, RedirectAttributes redirectAttributes) {

        route.setStatus(STATUS_OPEN);
        try {
            routeService.save(routeMapper.toDTO(route));
            redirectAttributes.addFlashAttribute("message", MESSAGE_STATUS_SAVE_SUCCESSFULLY + route.getNumber());

        } catch (Exception e) {
            redirectAttributes.addAttribute("message", MESSAGE_STATUS_SAVE_UNSUCCESSFULLY + route.getNumber());
        }

        return "redirect:/admin/routes";
    }

    /**
     * The method changeStatus() changes the current status of the route
     */

    @GetMapping("/admin/edit/{id}/{number}/{status}")
    public String changeStatus(@PathVariable("id") Integer id, @PathVariable("number") Integer number,
                               @PathVariable("status") String status, RedirectAttributes redirectAttributes) {

        try {

            routeService.updateStatusRoute(id, status);
            redirectAttributes.addFlashAttribute("message", MESSAGE_STATUS_CHANGE_SUCCESSFULLY + number);

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute("message", MESSAGE_STATUS_CHANGE_UNSUCCESSFULLY + number);
        }
        return "redirect:/admin/routes";
    }

    /**
     * The method getCars() returns a list of all cars
     */
    @GetMapping("/admin/cars")
    public String getCars(Model model) {

        List<CarDto> cars = carService.getCars();
        model.addAttribute("cars", cars);

        return "admin/cars";
    }

    /**
     * The method deleteCar() deletes the current record from the database by id
     */
    @GetMapping("/admin/car/delete/{id}/{number}")
    public String deleteCar(@PathVariable("id") Integer id, @PathVariable("number") String number,
                            RedirectAttributes redirectAttributes) {
        try {
            carService.deleteCarById(id);

            redirectAttributes.addFlashAttribute("message", MESSAGE_CAR_DELETE_SUCCESSFULLY + number);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", MESSAGE_CAR_DELETE_UNSUCCESSFULLY + number);
        }

        return "redirect:/admin/cars";
    }

    /**
     * The method addCar() creates a new Car() object and passes it to the view
     */

    @GetMapping("/admin/car/new")
    public String addCar(Model model) {

        model.addAttribute("car", new Car());
        model.addAttribute("pageTitle", "Create new car:");

        return "admin/car_form";
    }

    /**
     * The method saveCar() adds a new route to the database from the returned view
     */
    @PostMapping("/admin/car/save")
    public String saveCar(@Valid Car car, RedirectAttributes redirectAttributes) {

        try {
            carService.save(carMapper.toDTO(car));
            redirectAttributes.addFlashAttribute("message", MESSAGE_CAR_SAVE_SUCCESSFULLY + car.getNumber());

        } catch (Exception e) {
            redirectAttributes.addAttribute("message", MESSAGE_CAR_SAVE_UNSUCCESSFULLY + car.getNumber());
        }

        return "redirect:/admin/cars";
    }

    /**
     * The method editCar() returns the current object from the view for later modification
     */
    @GetMapping("/admin/car/{id}")
    public String editCars(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {

            model.addAttribute("car", carService.getById(id));

            return "admin/car_form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/admin/cars";
        }
    }

    /**
     * The method getUsers() returns a list of all users
     */
    @GetMapping("/admin/users")
    public String getUsersNotBlocked(Model model) {

        List<LoginDto> logins = userService.getAllLoginNotBlocked("%" + BLOCKED);
        model.addAttribute("users", logins);

        return "admin/users";
    }

    /**
     * The method addUser() creates a new Login() object and passes it to the view
     */

    @GetMapping("/admin/user/new")
    public String addUser(Model model) {

        model.addAttribute("login", new Login());
        model.addAttribute("user", new UserDto());
        model.addAttribute("pageTitle", "Create new user:");

        return "admin/user_form";
    }

    /**
     * The method saveCar() adds a new route to the database from the returned view
     */
    @PostMapping("/admin/user/new")
    public String saveCar(@Valid @ModelAttribute("login") Login login, BindingResult bindingResult,
                          @Valid @ModelAttribute("user") UserDto userDto, BindingResult bindingResult2,
                          RedirectAttributes redirectAttributes) {

        loginValidator.validate(login, bindingResult);

        if (bindingResult.hasErrors()) {
            return "admin/user_form";
        }
        if (bindingResult2.hasErrors()) {
            return "admin/user_form";
        }

        try {
            userService.saveUser(userDto);
            userService.saveLogin(login, userDto);

            redirectAttributes.addFlashAttribute("message", MESSAGE_USER_DELETE_SUCCESSFULLY + userDto.getName());

        } catch (Exception e) {

            redirectAttributes.addAttribute("message", MESSAGE_USER_DELETE_UNSUCCESSFULLY + userDto.getName());
        }

        return "redirect:/admin/users";
    }

    /**
     * The method deleteCar() deletes the current record from the database by id
     */

    @GetMapping("/admin/user/delete/{id}/{name}/{role}")
    public String editRoleLogin(@PathVariable("id") Integer id, @PathVariable("role") String role,
                                @PathVariable("name") String name, RedirectAttributes redirectAttributes) {
        try {
            userService.editRoleLogin(id, role + BLOCKED);

            redirectAttributes.addFlashAttribute("message", MESSAGE_CAR_DELETE_SUCCESSFULLY + name);
        } catch (Exception e)
        {
            redirectAttributes.addFlashAttribute("message", MESSAGE_CAR_DELETE_UNSUCCESSFULLY + name);
        }

        return "redirect:/admin/users";
    }

}