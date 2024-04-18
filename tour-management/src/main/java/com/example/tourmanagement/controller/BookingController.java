package com.example.tourmanagement.controller;

import com.example.tourmanagement.model.Invoice;
import com.example.tourmanagement.model.Tour;
import com.example.tourmanagement.service.TourService;
import com.example.tourmanagement.service.InvoiceService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Optional;

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
            model.addAttribute("invoice", new Invoice());
            return "book/book";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Tour not found!");
            return "redirect:/book";
        }
    }

    @PostMapping("/submit")
    public String submitBooking(@ModelAttribute Invoice invoice, HttpSession session, RedirectAttributes redirectAttributes) {
        session.setAttribute("currentInvoice", invoice);
        redirectAttributes.addFlashAttribute("successMessage", "Booking submitted successfully!");
        return "redirect:/payment";
    }

    @GetMapping("/confirm")
    public String confirmBooking(HttpSession session, RedirectAttributes redirectAttributes) {
        Invoice invoice = (Invoice) session.getAttribute("currentInvoice");
        if (invoice != null) {
            invoiceService.saveInvoice(invoice);
            session.removeAttribute("currentInvoice");
            redirectAttributes.addFlashAttribute("successMessage", "Booking confirmed and payment completed!");
            return "redirect:/invoice/" + invoice.getId();
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "No booking found to confirm.");
            return "redirect:/book";
        }
    }

    @GetMapping
    public String viewAllBookings(Model model) {
        model.addAttribute("listOfInvoices", invoiceService.getAllInvoices());
        return "listBookings";
    }
}
