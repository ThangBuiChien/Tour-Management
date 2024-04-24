package com.example.tourmanagement.service;

import com.example.tourmanagement.model.Invoice;
import java.util.List;

public interface InvoiceService {
    Invoice saveInvoice(Invoice invoice);
    List<Invoice> getAllInvoices();
    Invoice findInvoiceById(Long id);
}
