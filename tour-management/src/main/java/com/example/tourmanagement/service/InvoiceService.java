package com.example.tourmanagement.service;

import com.example.tourmanagement.model.Invoice;
import com.example.tourmanagement.model.Tour;

import java.util.List;

public interface InvoiceService {
    Invoice saveInvoice(Invoice invoice);
    List<Invoice> getAllInvoices();
    Invoice findInvoiceById(Long id);
    List<Invoice> findInvoicesByTour(Tour tour);
}
