package com.example.tourmanagement.controller;

import com.example.tourmanagement.model.Tour_Assignment;
import com.example.tourmanagement.service.TourGuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/assignments")
public class AssignmentController {

    private final TourGuideService tourGuideService;

    @Autowired
    public AssignmentController(TourGuideService tourGuideService) {
        this.tourGuideService = tourGuideService;
    }

    @GetMapping
    public String assignTourToGuide() {
        return "tour_guide/tour_assign";
    }

    @GetMapping("/{guideId}")
    public void getAssignmentsByTourGuide(@PathVariable Long guideId) {
    }
}