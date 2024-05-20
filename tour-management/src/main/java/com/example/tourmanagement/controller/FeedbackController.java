package com.example.tourmanagement.controller;

import com.example.tourmanagement.model.Feedback;
import com.example.tourmanagement.model.Tour;
import com.example.tourmanagement.model.UserModel;
import com.example.tourmanagement.service.FeedbackService;
import com.example.tourmanagement.service.TourService;
import com.example.tourmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/feedback")
@PreAuthorize("hasRole('ADMIN')")
public class FeedbackController {

    private final FeedbackService feedbackService;
    private final UserService userService;
    private final TourService tourService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService, UserService userService, TourService tourService ){
        this.feedbackService = feedbackService;
        this.userService = userService;
        this.tourService = tourService;
    }

    @GetMapping({"/", ""})
    public String viewHomePage(Model model){
        return "redirect:/feedback/load";
    }

    @GetMapping("/load")
    public String loadFeedback(Model model){
        List<Feedback> feedbacks = feedbackService.getAllFeedback();
        model.addAttribute("ListFeedbacks",feedbacks);
        return "feedback/feedback_home";
    }

    @PostMapping("/add")
    public String addFeedback(Model model, @ModelAttribute("feedback") Feedback feedback, @RequestParam("tourId") long tourId, RedirectAttributes redirectAttributes, @AuthenticationPrincipal UserDetails userDetails) {
        String userMail = userDetails.getUsername();
        Optional<UserModel> userOpt = userService.loadByEmail(userMail);
        if (userOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "User not found!");
            return "redirect:/tour/available";
        }
        UserModel user = userOpt.get();
        Optional<Tour> tour = tourService.findByID(tourId);
        if (tour.isPresent()) {
            feedback.setTour(tour.get()); // Set the tour
            feedback.setUserModel(user); // Set the user
            this.feedbackService.saveFeedback(feedback);
            return "redirect:/tour/detailed/" + tourId;
        } else {
            model.addAttribute("message", "Tour not found!");
            return "err/error";  // Or any appropriate error handling view
        }
    }



    @PostMapping("/delete/{id}")
    public String deleteFeedback(Model model, @PathVariable long id){
        this.feedbackService.deleteFeedback(id);
        return "redirect:/feedback";
    }

    @PostMapping("update/{id}")
    public  String updateFeedback(Model model, @PathVariable long id, @ModelAttribute("feedback") Feedback updatedFeedback){
        Optional<Feedback> optionalFeedback = feedbackService.findByID(id);
        if(optionalFeedback.isPresent()){
            updatedFeedback.setId(id);
            this.feedbackService.saveFeedback(updatedFeedback);
            return "redirect:/feedback";
        }
        else{
            return "redirect:/feedback";
        }
    }

    @GetMapping("/showAddForm")
    public String showAddForm(Model model){
        Feedback feedback = new Feedback();
        model.addAttribute("feedback", feedback);

        List<UserModel> userModels = userService.getAllUser();
        model.addAttribute("users", userModels);

        List<Tour> tours = tourService.getAllTour();
        model.addAttribute("tours",tours);

        return "feedback/add_feedback";
    }
}

