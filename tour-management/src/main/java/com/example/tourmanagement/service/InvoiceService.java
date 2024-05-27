package com.example.tourmanagement.service;

import com.example.tourmanagement.model.Invoice;
import com.example.tourmanagement.model.InvoiceStatus;
import com.example.tourmanagement.model.Tour;
import com.example.tourmanagement.repository.InvoiceRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InvoiceService {
    Invoice saveInvoice(Invoice invoice);
    List<Invoice> getAllInvoices();
    Invoice findInvoiceById(Long id);
    List<Invoice> findInvoicesByTour(Tour tour);

    void updateInvoiceStatus(Long invoiceId, InvoiceStatus status);

    Invoice updateInvoice(Invoice invoice);
    Page<Invoice> getAllInvoices(Pageable pageable);
    Page<Invoice> getInvoicesByUser(Pageable pageable, String email);

    Page<Invoice> getInvoiceById(Pageable pageable, Long id);



}
