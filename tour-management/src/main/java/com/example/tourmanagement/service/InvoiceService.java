package com.example.tourmanagement.service;

import com.example.tourmanagement.model.Invoice;
import com.example.tourmanagement.model.InvoiceStatus;
import com.example.tourmanagement.repository.InvoiceRepo;

import java.util.List;

public interface InvoiceService {
    Invoice saveInvoice(Invoice invoice);
    List<Invoice> getAllInvoices();
    Invoice findInvoiceById(Long id);

    void updateInvoiceStatus(Long invoiceId, InvoiceStatus status);

    Invoice updateInvoice(Invoice invoice);
}
