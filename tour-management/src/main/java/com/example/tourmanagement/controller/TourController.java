package com.example.tourmanagement.controller;

import com.example.tourmanagement.model.*;
import com.example.tourmanagement.service.CapacityService;
import com.example.tourmanagement.service.FeedbackService;
import com.example.tourmanagement.service.RouteService;
import com.example.tourmanagement.service.TourService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tour")
@PreAuthorize("hasRole('ADMIN')")
public class TourController {
    private final TourService tourService;
    private final RouteService routeService;
    private final FeedbackService feedbackService;

    private final CapacityService capacityService;

    public  TourController(TourService tourService, RouteService routeService, FeedbackService feedbackService, CapacityService capacityService){
        this.tourService = tourService;
        this.routeService = routeService;
        this.feedbackService = feedbackService;
        this.capacityService = capacityService;
    }

    @GetMapping({"/", ""})
    public String viewHomePage(Model model){
        return "redirect:/tour/sorting";
    }

    @GetMapping("/load")
    public String getAllRoute(Model model, RedirectAttributes redirectAttributes){

        if (redirectAttributes.containsAttribute("successMessage")) {
            model.addAttribute("successMessage", redirectAttributes.getAttribute("successMessage"));
        }
        model.addAttribute("listTours",  tourService.getAllTour());
        model.addAttribute("availableTours",  tourService.getAllTour());

        return "tour/available_tours";

    }

    @GetMapping("/sorting")
    public String getAllRoutePage(Model model, RedirectAttributes redirectAttributes,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "5") int size,
                                  @RequestParam(defaultValue = "id") String sortBy,
                                  @RequestParam(defaultValue = "") String keyword){

        if (redirectAttributes.containsAttribute("successMessage")) {
            model.addAttribute("successMessage", redirectAttributes.getAttribute("successMessage"));
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
//        Page<Tour> listTours = tourService.getAllTour(pageable, keyword);
//        model.addAttribute("listTours", listTours);
        Page<Tour> availableTours = tourService.getAllTour(pageable, keyword);
        model.addAttribute("availableTours", availableTours);
        int totalPages = availableTours.getTotalPages();

        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        model.addAttribute("size", size);

        return "tour/available_tours";

    }

    @GetMapping("/admin_tours")
    public String getAllTourPage(Model model, RedirectAttributes redirectAttributes,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "5") int size,
                                  @RequestParam(defaultValue = "id") String sortBy,
                                  @RequestParam(defaultValue = "") String keyword){

        if (redirectAttributes.containsAttribute("successMessage")) {
            model.addAttribute("successMessage", redirectAttributes.getAttribute("successMessage"));
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Tour> listTours = tourService.getAllTour(pageable, keyword);
        model.addAttribute("listTours", listTours);
//        Page<Tour> availableTours = tourService.getAllTour(pageable, keyword);
//        model.addAttribute("availableTours", availableTours);
        int totalPages = listTours.getTotalPages();

        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        model.addAttribute("size", size);

        return "tour/tour_home";

    }


//    @PostMapping("/add")
//    public String addRoute(@ModelAttribute("tour") Tour tour, RedirectAttributes redirectAttributes){
//        tour.setLengthTrip(tour.getDetailRoute().getLengthTrip());
//        tour.setTourDescription(tour.getDetailRoute().getDetailTrip());
//        tour.setTourStatus("available");
//        tour.setRegister(0);
//        tour.setTourName(tour.getDetailRoute().getRoute().getEndLocation() + tour.getDetailRoute().getStopLocation());
//
//        this.tourService.saveTour(tour);
//        redirectAttributes.addFlashAttribute("successMessage", "Department added successfully!");
//       // return "redirect:/tour/load";
//        return "redirect:/tour/sorting";
//
//    }

    @PostMapping("/add")
    public String addRoute(@ModelAttribute("tour") Tour tour,
                           @RequestParam("startDates") List<LocalDate> startDates,
                           RedirectAttributes redirectAttributes){
        tour.setLengthTrip(tour.getDetailRoute().getLengthTrip());
        tour.setTourDescription(tour.getDetailRoute().getDetailTrip());
        tour.setTourStatus("available");
        tour.setRegister(0);
        tour.setTourName(tour.getDetailRoute().getRoute().getEndLocation() + tour.getDetailRoute().getStopLocation());

        for (LocalDate startDate : startDates) {
            // Create a new Tour object for each start date
            Tour tourCopy = new Tour();
            tourCopy.setDetailRoute(tour.getDetailRoute());
            tourCopy.setTourCapacity(tour.getTourCapacity());
            tourCopy.setTourPrice(tour.getTourPrice());
            tourCopy.setStartDate(startDate);

            tourCopy.setLengthTrip(tour.getDetailRoute().getLengthTrip());
            tourCopy.setTourDescription(tour.getDetailRoute().getDetailTrip());
            tourCopy.setTourStatus("available");
            tourCopy.setRegister(0);
            tourCopy.setTourName(tour.getDetailRoute().getRoute().getEndLocation() + tour.getDetailRoute().getStopLocation());

            this.tourService.saveTour(tourCopy);
        }
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
            return "redirect:/tour/sorting";
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
//        List<Tour> availableTours = tourService.getAllTour().stream()
//                .filter(tour -> "available".equalsIgnoreCase(tour.getTourStatus()))
//                .toList();
        List<Tour> availableTours = tourService.getAllTour();
        model.addAttribute("availableTours", availableTours);
        return "tour/available_tours";
    }

    @GetMapping("/showUpdateForm/{id}")
    public String showUpdateForm(Model model, @PathVariable long id) {
        Optional<Tour> tour = tourService.findByID(id);
        if (tour.isPresent()) {
            model.addAttribute("tour", tour.get());

            List<Route> routes = routeService.getAllRoute();
            model.addAttribute("routes", routes);
            List<Capacity> capacities = capacityService.getAllCapacity();
            model.addAttribute("capacities", capacities);

            return "tour/updated_tour";

        } else {
            model.addAttribute("message", "Detail Route is can not found!");
            return "redirect:/tour";
        }
    }

    @GetMapping("/detailed/{id}")
    public String showDetail(Model model, @PathVariable long id) {
        Optional<Tour> tour = tourService.findByID(id);
        List<Feedback> feedbacks = feedbackService.findAllByTourId(id);  // Assuming this method is available
        if (tour.isPresent()) {
            model.addAttribute("tour", tour.get());
            Feedback feedback = new Feedback();
            model.addAttribute("feedbacks", feedbacks);
            model.addAttribute("feedback", feedback);
            return "tour/detailed_tour";
        } else {
            model.addAttribute("message", "Detail Route is cannot be found!");
            return "tour/available_tours";
        }
    }


    @PostMapping("/clone/{id}")
    public String cloneTour(@PathVariable Long id, Model model  ){
        Optional<Tour> tour = tourService.findByID(id);
        if(tour.isPresent()){
            Tour tourCopy = new Tour();
//            tourCopy.setDetailRoute(tour.get().getDetailRoute());
//            tourCopy.setTourCapacity(tour.get().getTourCapacity());
//            tourCopy.setTourPrice(tour.get().getTourPrice());
//
//            tourCopy.setLengthTrip(tour.get().getDetailRoute().getLengthTrip());
//            tourCopy.setTourDescription(tour.get().getDetailRoute().getDetailTrip());
//            tourCopy.setTourStatus("available");
//            tourCopy.setRegister(0);
//            tourCopy.setTourName(tour.get().getDetailRoute().getRoute().getEndLocation() + tour.get().getDetailRoute().getStopLocation());
            tourCopy.setTourName(tour.get().getTourName());
            tourCopy.setDetailRoute(tour.get().getDetailRoute());
            tourCopy.setTourCapacity(tour.get().getTourCapacity());
            tourCopy.setTourPrice(tour.get().getTourPrice());


            model.addAttribute("tour", tourCopy);
            model.addAttribute("routes", tour.get().getDetailRoute().getRoute());
//            model.addAttribute("tour", tourCopy);
            model.addAttribute("capacities", tour.get().getTourCapacity());

            return "tour/add_tour";
        }
        else{
            return "redirect:/tour";

        }

    }




}
