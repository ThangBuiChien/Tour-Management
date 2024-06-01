package com.example.tourmanagement.controller;

import com.example.tourmanagement.depa.PaymentService;
import com.example.tourmanagement.depa.PaymentServiceFactory;
import com.example.tourmanagement.service.DetailRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/depa")
public class depaTestingController {
    private final PaymentServiceFactory paymentServiceFactory;

    @Autowired
    public depaTestingController(PaymentServiceFactory paymentServiceFactory) {
        this.paymentServiceFactory = paymentServiceFactory;
    }




    @PostMapping("/payment")
    public String processPayment(Model model,
                                 @RequestParam("paymentMethod") String paymentMethod,
                                 @RequestParam("amount") double amount) {
        PaymentService paymentService = paymentServiceFactory.createPaymentService(paymentMethod);
        String message = paymentService.processPayment(amount);
        model.addAttribute("message", message);
        return "depa/payment";
    }

}
