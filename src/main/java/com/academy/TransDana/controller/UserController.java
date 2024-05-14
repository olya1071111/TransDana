package com.academy.TransDana.controller;

import com.academy.TransDana.dto.RouteDto;
import com.academy.TransDana.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final RouteService routeService;
    public final static String MESSAGE_STATUS_CHANGE_UNSUCCESSFULLY = "Status was not be change Route № ";
    public final static String MESSAGE_STATUS_CHANGE_SUCCESSFULLY = "Status was be change Route № ";

    /**
     * The method getRoute() returns a list of all routes with the specified date period for each driver account.
     * It is necessary to set the parameters of the start date period and the end date of the route presentation period
     */

    @GetMapping("/users/routes")
    public String getRoutesForUser(Model model,
                                   @Param("arrivalDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date arrivalDate,
                                   @Param("departureDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date departureDate,
                                   @RequestParam(defaultValue = "1") int page,
                                   @RequestParam(defaultValue = "9") int size) {

        try {

            Page<RouteDto> pageTuts = routeService.getAllRoutesByUserAndArrivalDateBetween(arrivalDate,
                    departureDate, page, size);

            model.addAttribute("routes", pageTuts.getContent());
            model.addAttribute("currentPage", pageTuts.getNumber() + 1);
            model.addAttribute("totalItems", pageTuts.getTotalElements());
            model.addAttribute("totalPages", pageTuts.getTotalPages());
            model.addAttribute("pageSize", size);
            model.addAttribute("arrivalDate", arrivalDate);
            model.addAttribute("departureDate", departureDate);
            model.addAttribute("name", pageTuts.getContent().get(0).getUser().getName());
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return "users/routes";
    }

    /**
     * The method changeStatus() changes the status of the selected route
     */

    @GetMapping("/users/edit/{id}/{number}/{status}")

    public String editChangeStatus(@PathVariable("id") Integer id, @PathVariable("number") Integer number,
                                   @PathVariable("status") String status, RedirectAttributes redirectAttributes) {

        try {
            routeService.updateStatusRoute(id, status);
            redirectAttributes.addFlashAttribute("message", MESSAGE_STATUS_CHANGE_SUCCESSFULLY + number);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", MESSAGE_STATUS_CHANGE_UNSUCCESSFULLY + number);
        }
        return "redirect:/users/routes";
    }
}
