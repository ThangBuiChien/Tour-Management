package com.example.tourmanagement.controller;

import com.example.tourmanagement.model.DetailRoute;
import com.example.tourmanagement.model.Route;
import com.example.tourmanagement.model.Tour;
import com.example.tourmanagement.service.RouteService;
import com.example.tourmanagement.service.TourService;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tour")

public class TourController {
    private final TourService tourService;
    private final RouteService routeService;

    public  TourController(TourService tourService, RouteService routeService){
        this.tourService = tourService;
        this.routeService = routeService;
    }

    @GetMapping({"/", ""})
    public String viewHomePage(Model model){
        return "redirect:/tour/load";
    }

    @GetMapping("/load")
    public String getAllRoute(Model model, RedirectAttributes redirectAttributes){

        if (redirectAttributes.containsAttribute("successMessage")) {
            model.addAttribute("successMessage", redirectAttributes.getAttribute("successMessage"));
        }
        model.addAttribute("listTours",  tourService.getAllTour());
        return "tour/tour_home";

    }

    @PostMapping("/add")
    public String addRoute(@ModelAttribute("tour") Tour tour, RedirectAttributes redirectAttributes){
        this.tourService.saveTour(tour);
        redirectAttributes.addFlashAttribute("successMessage", "Department added successfully!");
        return "redirect:/tour/load";

    }

    @PostMapping("/delete/{id}")
    public String deleteRoute(@PathVariable Long id){
        Optional<Tour> department = tourService.findByID(id);
        if(department.isPresent()){
            this.tourService.deleteTour(id);
            return "redirect:/tour";
        }
        else{
            return "redirect:/tour";

        }

    }

    @PostMapping("/update/{id}")
    public String updateDepartment(@PathVariable Long id, @ModelAttribute("tour") Tour updatedTour) {
        Optional<Tour> optionalDepartment = tourService.findByID(id);
        if (optionalDepartment.isPresent()) {
            updatedTour.setId(id);
            tourService.saveTour(updatedTour);
            return "redirect:/tour";
        } else {
            return "redirect:/tour";
        }
    }

    @GetMapping("/showAddForm")
    public String showAddForm(Model model){
        Tour tour = new Tour();
        model.addAttribute("tour", tour);
        List<Route> routes = routeService.getAllRoute();
        model.addAttribute("routes", routes);
        return "tour/add_tour";
    }

    // Method to handle AJAX requests for fetching detail routes
    @GetMapping("/getDetailRoutes")
    @ResponseBody // This annotation tells Spring to serialize the return value directly to the HTTP response body
    public List<DetailRoute> getDetailRoutes(@RequestParam("routeId") Long routeId) {
        Optional<Route> route = routeService.findByID(routeId);
        if (route.isPresent()) {
            return route.get().getDetailRouteList();
        } else {
            // Return an empty list or handle the error case accordingly
            return Collections.emptyList();
        }
    }



//    @GetMapping("/showUpdateForm/{id}")
//    public String showUpdateForm(Model model, @PathVariable long id) {
//        Optional<Route> route = routeServices.findByID(id);
//        if (route.isPresent()) {
//            model.addAttribute("route", route.get());
//            return "route/updated_route";
//
//        } else {
//            model.addAttribute("message", "Detail Route is can not found!");
//            return "redirect:/route";
//        }
//    }


}