package com.example.tourmanagement.controller;

import com.example.tourmanagement.model.Tour;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
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
    public String viewHomePage(Model model,
                               @AuthenticationPrincipal UserDetails userDetails,
                               HttpSession session){

        if(userDetails != null){
            session.setAttribute("email", userDetails.getUsername());

        }


        return "index";
       //x return "index";

    }
}
