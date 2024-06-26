package com.example.tourmanagement.controller;

import com.example.tourmanagement.model.Invoice;
import com.example.tourmanagement.model.Tour;
import com.example.tourmanagement.model.TourAssignment;
import com.example.tourmanagement.model.TourGuide;
import com.example.tourmanagement.service.InvoiceService;
import com.example.tourmanagement.service.TourAssignmentService;
import com.example.tourmanagement.service.TourGuideService;
import com.example.tourmanagement.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/assignments")
public class AssignmentController {

    private final TourAssignmentService tourAssignmentService;
    private final TourService tourService;
    private final TourGuideService tourGuideService;

    private final InvoiceService invoiceService;

    @Autowired
    public AssignmentController(TourAssignmentService tourAssignmentService,InvoiceService invoiceService, TourService tourService, TourGuideService tourGuideService) {
        this.tourAssignmentService = tourAssignmentService;
        this.tourService = tourService;
        this.tourGuideService = tourGuideService;
        this.invoiceService = invoiceService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public String showTourAssignments(@RequestParam(defaultValue = "0") long tourGuideId,Model model) {
        // Show available tour assignments
        Object[] tourAssignmentList = null;
        // Show adding tour assignment form
        List<Tour> tours = tourService.getAllTour();
        List<TourGuide> tourGuides = tourGuideService.getAllTourGuides();
        if (tourGuideId == 0)
        {
            tourAssignmentList = tourAssignmentService.getAllTourAssignment().stream().map(i -> Arrays.asList(i.getTour(), i.getTourGuide())).toArray();
        }
        else {
            tourAssignmentList = tourAssignmentService.findByTourGuide(tourGuideService.findById(tourGuideId).get()).stream().map(i -> Arrays.asList(i.getTour(), i.getTourGuide())).toArray();
        }
        model.addAttribute("tourAssignmentList", tourAssignmentList);
        //
        model.addAttribute("tours", tours);
        model.addAttribute("tourguides", tourGuides);
        TourAssignment tourAssignment = new TourAssignment();
        model.addAttribute(tourAssignment);
        return "tour_assignment/tour_assign";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public String addTourAssignment(@ModelAttribute("tourAssignment") TourAssignment tourAssignment){
        // Validate received tourAssignment

        //
        tourAssignmentService.save(tourAssignment);
        return "redirect:/assignments";
    }
    @GetMapping("/members")
    public String showTourMembers(@RequestParam("tourId") Long tourId, Model model) {
        Tour tour = tourService.findByID(tourId).orElseThrow(() -> new RuntimeException("Tour not found!"));
        List<Invoice> invoices = invoiceService.findInvoicesByTour(tour);
        List<String> members = invoices.stream()
                .flatMap(invoice -> invoice.getListOfMember().stream())
                .distinct()
                .collect(Collectors.toList());
        model.addAttribute("members", members);
        return "tour_assignment/tour_members";
    }

    @GetMapping("/loadTourGuide")
    public String loadTourGuide(Model model) {
        List<TourGuide> tourGuides = tourGuideService.getAllTourGuides();

        model.addAttribute("tourGuides", tourGuides);
        return "tour_assignment/tour_assign";
    }

    @GetMapping("/loadByTourGuide")
    public String showTourAssignmentsTourGuide(@RequestParam(defaultValue = "0") long tourGuideId,Model model) {
        // Show available tour assignments
        Object[] tourAssignmentList = null;
        // Show adding tour assignment form
        List<Tour> tours = null;
        List<TourGuide> tourGuides = null;
        if (tourGuideId == 0)
        {
            tourAssignmentList = tourAssignmentService.getAllTourAssignment().stream().map(i -> Arrays.asList(i.getTour(), i.getTourGuide())).toArray();
        }
        else {
            tourAssignmentList = tourAssignmentService.findByTourGuide(tourGuideService.findById(tourGuideId).get()).stream().map(i -> Arrays.asList(i.getTour(), i.getTourGuide())).toArray();
        }
        model.addAttribute("tourAssignmentList", tourAssignmentList);
        //
        model.addAttribute("tours", tours);
        model.addAttribute("tourguides", tourGuides);
        TourAssignment tourAssignment = new TourAssignment();
        model.addAttribute(tourAssignment);
        return "tour_assignment/tour_assign";
    }



}