package com.example.tourmanagement.controller;

import ch.qos.logback.core.model.Model;
import com.example.tourmanagement.model.Invoice;
import com.example.tourmanagement.model.Payment;
import com.example.tourmanagement.service.InvoiceService;
import com.example.tourmanagement.service.PaymentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/submit")
    public String submitPayment(@ModelAttribute Payment payment, HttpSession session, RedirectAttributes redirectAttributes) {
        session.setAttribute("currentPayment", payment);
        redirectAttributes.addFlashAttribute("successMessage", "Payment submitted successfully!");
        return "redirect:/payment/payment_complete";
    }

    @GetMapping("/confirm")
    public String confirmPayment(HttpSession session, RedirectAttributes redirectAttributes) {
        Payment payment = (Payment) session.getAttribute("currentPayment");
        if (payment != null) {
            paymentService.savePayment(payment);
            session.removeAttribute("currentPayment");
            redirectAttributes.addFlashAttribute("successMessage", "Payment confirmed!");
            return "redirect:/payment/" + payment.getId();
        }
        else {
            redirectAttributes.addFlashAttribute("errorMessage", "No payment found to confirm.");
            return "redirect:/payment";
        }
    }

}
