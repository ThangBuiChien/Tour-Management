package com.example.tourmanagement.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jdk.jfr.Category;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        // Get the status code from the request
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            // Handle different error codes
            if (statusCode == 404) {
                return "err/error404"; // Return 404 error page
            } else if (statusCode == 500) {
                return "err/error500"; // Return 500 error page
            }
            else if (statusCode == 403) {
                return "err/error403"; // Return 500 error page
            }
            // Add more conditions for other error codes as needed
        }

        // Default error page if status code is not recognized
        return "err/error";
    }



}
