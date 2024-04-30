package com.example.tourmanagement.controller;

import com.example.tourmanagement.model.Invoice;
import com.example.tourmanagement.model.InvoiceStatus;
import com.example.tourmanagement.model.Tour;
import com.example.tourmanagement.model.Capacity;
import com.example.tourmanagement.service.TourService;
import com.example.tourmanagement.service.InvoiceService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;
import java.util.Optional;
import java.time.LocalDate;

@Controller
@RequestMapping("/book")
public class BookingController {

    private final TourService tourService;
    private final InvoiceService invoiceService;

    public BookingController(TourService tourService, InvoiceService invoiceService) {
        this.tourService = tourService;
        this.invoiceService = invoiceService;
    }

    @GetMapping("/{tourId}")
    public String showBookingForm(@PathVariable Long tourId, Model model, RedirectAttributes redirectAttributes) {
        Optional<Tour> tour = tourService.findByID(tourId);
        if (tour.isPresent()) {
            model.addAttribute("tour", tour.get());
            model.addAttribute("invoice", new Invoice());  // Make sure the invoice has a tour set if needed
            return "book/book";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Tour not found!");
            return "redirect:/tour/available";
        }
    }

    @PostMapping("/submit")
    public String submitBooking(@ModelAttribute Invoice invoice, @RequestParam("numMembers") int numMembers, @RequestParam("tourId") Long tourId, HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        // Debug logs to check received parameters
        System.out.println("Number of members: " + numMembers);
        System.out.println("Received tour ID: " + tourId);

        // Fetch the tour from the database
        Optional<Tour> tourOpt = tourService.findByID(tourId);
        if (tourOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Tour not found!");
            return "redirect:/tour/available";
        }

        Tour tour = tourOpt.get();
        invoice.setTour(tour); // Associate the tour with the invoice

        // Check number of members
        if (numMembers <= 0) {
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid number of members.");
            return "redirect:/tour/available";
        }

        // Check capacity constraints
        Capacity capacity = tour.getTourCapacity();
        if (numMembers > capacity.getMaxMember()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Number of members exceeds the tour capacity!");
            return "redirect:/book/" + tour.getId();
        }

        // Calculate total price
        float tourPrice = tour.getTourPrice();
        invoice.setTotalPrice(numMembers * tourPrice);

        //Set status for invoice
        invoice.setStatus(InvoiceStatus.PENDING);

        // Save the invoice
        try {
            invoiceService.saveInvoice(invoice);
            session.setAttribute("currentInvoice", invoice);
            model.addAttribute("invoice", invoice);
            redirectAttributes.addFlashAttribute("successMessage", "Booking submitted successfully!");
            return "redirect:/payment/payment_home";
        } catch (Exception e) {
            System.err.println("Error saving invoice: " + e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to save booking.");
            return "redirect:/tour/available";
        }
    }


    @PostMapping("/submitPayment")
    public String submitPayment(@ModelAttribute("invoice") Invoice invoice, @RequestParam Map<String, String> params, RedirectAttributes redirectAttributes) {

        invoice.setPaymentAccount(params.get("paymentAccount"));
        invoice.setPayerName(params.get("payerName"));


        invoice.setPaymentDate(LocalDate.now());


        invoiceService.updateInvoice(invoice);


        redirectAttributes.addFlashAttribute("successMessage", "Payment submitted successfully!");


        return "redirect:/payment/payment_complete";
    }



}
