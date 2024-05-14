package com.academy.TransDana.controller;

import com.academy.TransDana.dto.CarDto;
import com.academy.TransDana.model.domain.Car;
import com.academy.TransDana.model.mapping.CarMapper;
import com.academy.TransDana.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;
    private final CarMapper carMapper;
    public final static String MESSAGE_CAR_DELETE_SUCCESSFULLY = "Delete the car № ";
    public final static String MESSAGE_CAR_DELETE_UNSUCCESSFULLY = "Don't delete the car № ";
    public final static String MESSAGE_CAR_SAVE_SUCCESSFULLY = "Save the car № ";
    public final static String MESSAGE_CAR_SAVE_UNSUCCESSFULLY = "Don't save the car № ";

    /**
     * The method getCars() returns a list of all cars
     */
    @GetMapping("/operator/cars")
    public String getCars(Model model) {

        List<CarDto> cars = carService.getCars();
        model.addAttribute("cars", cars);

        return "operator/cars";
    }

    /**
     * The method deleteCar() deletes the current record from the database by id
     */
    @GetMapping("/operator/car/delete/{id}/{number}")
    public String deleteCar(@PathVariable("id") Integer id, @PathVariable("number") String number,
                            Model model, RedirectAttributes redirectAttributes) {
        try {
            carService.deleteCarById(id);

            redirectAttributes.addFlashAttribute("message", MESSAGE_CAR_DELETE_SUCCESSFULLY + number);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", MESSAGE_CAR_DELETE_UNSUCCESSFULLY + number);
        }

        return "redirect:/operator/cars";
    }

    /**
     * The method addCar() creates a new Car() object and passes it to the view
     */

    @GetMapping("/operator/car/new")
    public String addCar(Model model) {

        model.addAttribute("car", new Car());
        model.addAttribute("pageTitle", "Create new car:");

        return "operator/car_form";
    }

    /**
     * The method saveCar() adds a new route to the database from the returned view
     */
    @PostMapping("/operator/car/save")
    public String saveCar(Car car, RedirectAttributes redirectAttributes) {

        try {
            carService.save(carMapper.toDTO(car));
            redirectAttributes.addFlashAttribute("message", MESSAGE_CAR_SAVE_SUCCESSFULLY + car.getNumber());

        } catch (Exception e) {
            redirectAttributes.addAttribute("message", MESSAGE_CAR_SAVE_UNSUCCESSFULLY + car.getNumber());
        }

        return "redirect:/operator/cars";
    }

    /**
     * The method editCar() returns the current object from the view for later modification
     */
    @GetMapping("/operator/car/{id}")
    public String editCars(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {

            model.addAttribute("car", carService.getById(id));

            return "operator/car_form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/operator/cars";
        }
    }
}
