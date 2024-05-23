package com.example.tourmanagement.controller;

import com.example.tourmanagement.model.DetailRoute;
import com.example.tourmanagement.model.Route;
import com.example.tourmanagement.service.DetailRouteService;
import com.example.tourmanagement.service.RouteService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/route")
@PreAuthorize("hasRole('ADMIN')")
public class RouteController {

    private final RouteService routeServices;


    public RouteController(RouteService routeServices){
        this.routeServices = routeServices;
    }

//    private final DetailRouteServices detailRouteServices;
//
//    public RouteController(DetailRouteServices detailRouteServices){
//        this.detailRouteServices = detailRouteServices;
//    }

    @GetMapping({"/", ""})
    public String viewHomePage(Model model){
        return "redirect:/route/load";
    }

    @GetMapping("/load")
    public String getAllRoute(Model model, RedirectAttributes redirectAttributes){

        if (redirectAttributes.containsAttribute("successMessage")) {
            model.addAttribute("successMessage", redirectAttributes.getAttribute("successMessage"));
        }
        model.addAttribute("listRoutes",  routeServices.getAllRoute());
        return "route/admin_route";

    }


    @PostMapping("/add")
    public String addRoute(@ModelAttribute("route") Route route, RedirectAttributes redirectAttributes){
        this.routeServices.saveRoute(route);
        redirectAttributes.addFlashAttribute("successMessage", "Route added successfully!");
        return "redirect:/route/load";

    }

    @PostMapping("/delete/{id}")
    public String deleteRoute(@PathVariable Long id){
        Optional<Route> department = routeServices.findByID(id);
        if(department.isPresent()){
            this.routeServices.deleteRoute(id);
            return "redirect:/route";
        }
        else{
            return "redirect:/route";

        }

    }

    @PostMapping("/update/{id}")
    public String updateDepartment(@PathVariable Long id, @ModelAttribute("route") Route updatedRoute) {
        Optional<Route> optionalDepartment = routeServices.findByID(id);
        if (optionalDepartment.isPresent()) {
            updatedRoute.setId(id);
            routeServices.saveRoute(updatedRoute);
            return "redirect:/route";
        } else {
            return "redirect:/route";
        }
    }

    @GetMapping("/showAddForm")
    public String showAddForm(Model model){
        Route route = new Route();
        model.addAttribute("route", route);
//        List<DetailRoute> detailRoutes = detailRouteServices.getAllDetailRoute();
//        model.addAttribute("detailRoutes", detailRoutes);
        return "route/admin_addRoute";
    }

    @GetMapping("/showUpdateForm/{id}")
    public String showUpdateForm(Model model, @PathVariable long id) {
        Optional<Route> route = routeServices.findByID(id);
        if (route.isPresent()) {
            model.addAttribute("route", route.get());
            return "route/updated_route";

        } else {
            model.addAttribute("message", "Detail Route is can not found!");
            return "redirect:/route";
        }
    }
}
