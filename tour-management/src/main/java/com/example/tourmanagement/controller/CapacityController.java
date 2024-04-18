package com.example.tourmanagement.controller;

import com.example.tourmanagement.model.Capacity;
import com.example.tourmanagement.model.DetailRoute;
import com.example.tourmanagement.model.Route;
import com.example.tourmanagement.service.CapacityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/capacity")
public class CapacityController {
    private final CapacityService capacityService;

    public  CapacityController(CapacityService capacityService){
        this.capacityService = capacityService;
    }

    @GetMapping({"/", ""})
    public String viewHomePage(Model model){


        return "redirect:/capacity/load";


    }

    @GetMapping("/load")
    public String getAllDepartment(Model model, RedirectAttributes redirectAttributes){

        if (redirectAttributes.containsAttribute("successMessage")) {
            model.addAttribute("successMessage", redirectAttributes.getAttribute("successMessage"));
        }
        model.addAttribute("listCapacities",  capacityService.getAllCapacity());
        return "capacity/capacity_home";

    }


    @PostMapping("/add")
    public String addDepartment(@ModelAttribute("capacity") Capacity capacity, RedirectAttributes redirectAttributes){
        if(capacity.getMinMember() < capacity.getMaxMember()) {
            this.capacityService.saveCapacity(capacity);
            redirectAttributes.addFlashAttribute("successMessage", "Department added successfully!");
        }
        else{
            redirectAttributes.addFlashAttribute("successMessage", "Max member must greater than Min member");
        }
        return "redirect:/capacity/load";

    }

    @GetMapping("/showAddForm")
    public String showAddForm(Model model){
        Capacity capacity = new Capacity();
        model.addAttribute("capacity", capacity);
        return "capacity/add_capacity";
    }

}
