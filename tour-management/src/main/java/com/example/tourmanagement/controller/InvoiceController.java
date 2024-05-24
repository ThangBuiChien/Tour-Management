package com.example.tourmanagement.controller;

import com.example.tourmanagement.model.Invoice;
import com.example.tourmanagement.model.InvoiceStatus;
import com.example.tourmanagement.service.InvoiceService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {
    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    // Update this from "/invoice" to just "/" to match the base mapping + this
    @GetMapping
    public String getAllInvoices(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Invoice> invoicePage = invoiceService.getAllInvoices(pageable);
        model.addAttribute("listInvoice", invoicePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", invoicePage.getTotalPages());
        return "invoice/invoice_home";
    }



    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/updateStatus/{id}")
    public String updateInvoiceStatus(@PathVariable Long id, @RequestParam("status") InvoiceStatus status, RedirectAttributes redirectAttributes) {
        invoiceService.updateInvoiceStatus(id, status);
        redirectAttributes.addFlashAttribute("successMessage", "Invoice status updated successfully!");
        return "redirect:/invoice"; // Updated this to redirect correctly
    }
}
