package com.example.tourmanagement.controller;

import com.example.tourmanagement.model.Capacity;
import com.example.tourmanagement.model.DetailRoute;
import com.example.tourmanagement.model.Route;
import com.example.tourmanagement.model.Tour;
import com.example.tourmanagement.service.CapacityService;
import com.example.tourmanagement.service.RouteService;
import com.example.tourmanagement.service.TourService;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    private final CapacityService capacityService;

    public  TourController(TourService tourService, RouteService routeService, CapacityService capacityService){
        this.tourService = tourService;
        this.routeService = routeService;
        this.capacityService = capacityService;
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

    @GetMapping("/sorting")
    public String getAllRoutePage(Model model, RedirectAttributes redirectAttributes,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "3") int size,
                                  @RequestParam(defaultValue = "id") String sortBy,
                                  @RequestParam(defaultValue = "") String keyword){

        if (redirectAttributes.containsAttribute("successMessage")) {
            model.addAttribute("successMessage", redirectAttributes.getAttribute("successMessage"));
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Tour> listTours = tourService.getAllTour(pageable, keyword);
        model.addAttribute("listTours", listTours);
        int totalPages = listTours.getTotalPages();

        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        model.addAttribute("size", size);

        return "tour/tour_home";

    }

    @PostMapping("/add")
    public String addRoute(@ModelAttribute("tour") Tour tour, RedirectAttributes redirectAttributes){
        tour.setLengthTrip(tour.getDetailRoute().getLengthTrip());
        tour.setTourDescription(tour.getDetailRoute().getDetailTrip());
        tour.setTourStatus("available");
        tour.setRegister(0);
        tour.setTourName(tour.getDetailRoute().getRoute().getEndLocation() + tour.getDetailRoute().getStopLocation());

        this.tourService.saveTour(tour);
        redirectAttributes.addFlashAttribute("successMessage", "Department added successfully!");
       // return "redirect:/tour/load";
        return "redirect:/tour/sorting";

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
        List<Capacity> capacities = capacityService.getAllCapacity();
        model.addAttribute("capacities", capacities);
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

    @GetMapping("/available")
    public String viewAvailableTours(Model model) {
        List<Tour> availableTours = tourService.getAllTour().stream()
                .filter(tour -> "available".equalsIgnoreCase(tour.getTourStatus()))
                .toList();
        model.addAttribute("availableTours", availableTours);
        return "tour/available_tours";
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
