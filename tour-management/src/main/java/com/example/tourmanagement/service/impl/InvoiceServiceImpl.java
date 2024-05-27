package com.example.tourmanagement.service.impl;

import com.example.tourmanagement.model.Invoice;
import com.example.tourmanagement.model.InvoiceStatus;
import com.example.tourmanagement.model.Tour;
import com.example.tourmanagement.service.TourService;
import com.example.tourmanagement.repository.InvoiceRepo;
import com.example.tourmanagement.service.InvoiceService;
import com.example.tourmanagement.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepo invoiceRepository;
    private final TourService tourService;
    @Autowired
    private NotificationService notificationService;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepo invoiceRepository, TourService tourService) {
        this.invoiceRepository = invoiceRepository;
        this.tourService = tourService;  // Initialize TourService
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

    @Override
    @Transactional
    public void updateInvoiceStatus(Long invoiceId, InvoiceStatus status) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found!"));
        invoice.setStatus(status);

        if (status == InvoiceStatus.CANCELLED) {
            List<String> members = invoice.getListOfMember();
            int numMembers = members.size();
            invoice.setListOfMember(new ArrayList<>());

            Tour tour = invoice.getTour();
            int newRegister = tour.getRegister() - numMembers;
            tour.setRegister(Math.max(0, newRegister));  // Ensure the register value does not go negative

            tourService.saveTour(tour);  // Persist changes to the Tour
        }

        invoiceRepository.save(invoice);

        // Create and send notification
        notificationService.createNotification(invoice.getUserModel(), "The status of your invoice'" +
                " id #" + invoiceId + " has been updated to " + status + "."
        + " Your trip name is " + invoice.getTour().getTourName() ) ;
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

    @Override
    @Transactional(readOnly = true)
    public Page<Invoice> getAllInvoices(Pageable pageable) {
        Pageable descendingPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").descending());
        return invoiceRepository.findAll(descendingPageable);
    }

    @Override
    public Page<Invoice> getInvoicesByUser(Pageable pageable, String email) {
        return invoiceRepository.findByUserModelEmail(pageable, email);
    }

    @Override
    public Page<Invoice> getInvoiceById(Pageable pageable, Long id) {
        return invoiceRepository.findById(pageable, id);
    }
}