package com.example.tourmanagement.service.impl;

import com.example.tourmanagement.model.Invoice;
import com.example.tourmanagement.model.Tour;
import com.example.tourmanagement.repository.InvoiceRepo;
import com.example.tourmanagement.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepo invoiceRepository;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepo invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    @Transactional
    public Invoice saveInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Invoice findInvoiceById(Long id) {
        return invoiceRepository.findById(id).orElseThrow(() -> new RuntimeException("Invoice not found!"));
    }
    @Override
    @Transactional(readOnly = true)
    public List<Invoice> findInvoicesByTour(Tour tour) {
        return invoiceRepository.findByTour(tour);
    }


}
