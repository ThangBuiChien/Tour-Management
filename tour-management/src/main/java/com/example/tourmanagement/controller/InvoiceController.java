package com.example.tourmanagement.controller;

import com.example.tourmanagement.service.InvoiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/load")
    public String getAllInvoices(Model model, RedirectAttributes redirectAttributes) {
        if (redirectAttributes.containsAttribute("successMessage")) {
            model.addAttribute("successMessage", redirectAttributes.getAttribute("successMessage"));
        }
        model.addAttribute("listInvoice", invoiceService.getAllInvoices());
        return "invoice/invoice_home";
    }
}
