package com.example.tourmanagement.controller;

import com.example.tourmanagement.model.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.tourmanagement.service.TourService;

@Controller
@RequestMapping("/")
public class AdminController {
    private final TourService tourService;

    public AdminController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping({"/", ""})
    public String viewHomePage(Model model){


        return "index";
       //x return "index";

    }

    @GetMapping({"/admin_addtour"})
    public String addTour(Model model){
        return "admin/admin_addtour";
    }

    @GetMapping({"/admin_edittour"})
    public String editTour(Model model){
        return "admin/admin_edittour";
    }

//    @GetMapping("/admin_tours")
//    public String getAllTourPage(Model model, RedirectAttributes redirectAttributes,
//                                 @RequestParam(defaultValue = "0") int page,
//                                 @RequestParam(defaultValue = "5") int size,
//                                 @RequestParam(defaultValue = "id") String sortBy,
//                                 @RequestParam(defaultValue = "") String keyword){
//
//        if (redirectAttributes.containsAttribute("successMessage")) {
//            model.addAttribute("successMessage", redirectAttributes.getAttribute("successMessage"));
//        }
//
//        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
//        Page<Tour> listTours = tourService.getAllTour(pageable, keyword);
//        model.addAttribute("listTours", listTours);
////        Page<Tour> availableTours = tourService.getAllTour(pageable, keyword);
////        model.addAttribute("availableTours", availableTours);
//        int totalPages = listTours.getTotalPages();
//
//        model.addAttribute("totalPages", totalPages);
//        model.addAttribute("currentPage", page);
//        model.addAttribute("keyword", keyword);
//        model.addAttribute("page", page);
//        model.addAttribute("size", size);
//
//        return "admin/admin_tours";
//
//    }
}
