package com.example.tourmanagement.service.impl;

import com.example.tourmanagement.model.Invoice;
import com.example.tourmanagement.model.InvoiceStatus;
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
    @Transactional
    public void updateInvoiceStatus(Long invoiceId, InvoiceStatus status) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found!"));
        invoice.setStatus(status);
        invoiceRepository.save(invoice);
    }
    @Override
    @Transactional
    public Invoice updateInvoice(Invoice invoice) {
        // Assuming the invoice ID is set, fetch the existing invoice, update and save it
        Invoice existingInvoice = invoiceRepository.findById(invoice.getId())
                .orElseThrow(() -> new RuntimeException("Invoice not found with id: " + invoice.getId()));

        // Update fields of the existing invoice
        existingInvoice.setPaymentAccount(invoice.getPaymentAccount());
        existingInvoice.setPayerName(invoice.getPayerName());
        existingInvoice.setPaymentDate(invoice.getPaymentDate());
        existingInvoice.setStatus(invoice.getStatus()); // Assuming you might want to update the status too

        // Save and return the updated invoice
        return invoiceRepository.save(existingInvoice);
    }
}
