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
@RequestMapping("/detail")
@PreAuthorize("hasRole('ADMIN')")
public class DetailRouteController {

    private final DetailRouteService detailRouteServices;

    private final RouteService routeService;

    public DetailRouteController(DetailRouteService detailRouteServices, RouteService routeService){
        this.detailRouteServices = detailRouteServices;
        this.routeService = routeService;
    }

    @GetMapping({"/", ""})
    public String viewHomePage(Model model){


        return "redirect:/detail/load";


    }

    @GetMapping("/load")
    public String getAllDepartment(Model model, RedirectAttributes redirectAttributes){

        if (redirectAttributes.containsAttribute("successMessage")) {
            model.addAttribute("successMessage", redirectAttributes.getAttribute("successMessage"));
        }
        model.addAttribute("listDetailRoutes",  detailRouteServices.getAllDetailRoute());
        return "detail_route/detail_route_home";

    }


    @PostMapping("/add")
    public String addDepartment(@ModelAttribute("detailRoute") DetailRoute detailRoute, RedirectAttributes redirectAttributes){
        this.detailRouteServices.saveDetailRoute(detailRoute);
        redirectAttributes.addFlashAttribute("successMessage", "Department added successfully!");
        return "redirect:/detail/load";

    }

    @PostMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable Long id){
        Optional<DetailRoute> department = detailRouteServices.findByID(id);
        if(department.isPresent()){
            this.detailRouteServices.deleteDetailRoute(id);
            return "redirect:/detail/load";
        }
        else{
            return "redirect:/detail/load";

        }

    }

    @PostMapping("/update/{id}")
    public String updateDepartment(@PathVariable Long id, @ModelAttribute("detailRoute") DetailRoute updatedDetailRoute) {
        Optional<DetailRoute> optionalDepartment = detailRouteServices.findByID(id);
        if (optionalDepartment.isPresent()) {
            updatedDetailRoute.setId(id);
            detailRouteServices.saveDetailRoute(updatedDetailRoute);
            return "redirect:/department";
        } else {
            return "redirect:/department";
        }
    }

    @GetMapping("/showAddForm")
    public String showAddForm(Model model){
        DetailRoute detailRoute = new DetailRoute();
        model.addAttribute("detailRoute", detailRoute);
        List<Route> routes = routeService.getAllRoute();
        model.addAttribute("routes", routes);
        return "detail_route/add_detail_route";
    }

    @GetMapping("/showUpdateForm/{id}")
    public String showUpdateForm(Model model, @PathVariable long id){
        Optional<DetailRoute> detailRoute = detailRouteServices.findByID(id);
        if(detailRoute.isPresent()){
            model.addAttribute("detailRoute", detailRoute.get());
            return "detail_route/updated_detail_route";

        }
        else{
            model.addAttribute("message", "Detail Route is can not found!");
            return "redirect:/detail";
        }
//        model.addAttribute("department", department.get());
//        return "department/updated_department";
    }



}
