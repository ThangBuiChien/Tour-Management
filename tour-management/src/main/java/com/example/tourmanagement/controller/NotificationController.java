package com.example.tourmanagement.controller;

import com.example.tourmanagement.model.UserModel;
import com.example.tourmanagement.service.NotificationService;
import com.example.tourmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private UserService userService;

    @GetMapping("/notifications")
    public String viewNotifications(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); // Assumes the username is the email of the user
        UserModel user = userService.loadByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("notifications", notificationService.getNotificationsForUser(user));
        return "notifications";
    }
}
