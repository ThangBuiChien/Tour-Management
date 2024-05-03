package com.example.tourmanagement.others;

import com.example.tourmanagement.model.Invoice;
import com.example.tourmanagement.model.InvoiceStatus;
import com.example.tourmanagement.model.Tour;
import com.example.tourmanagement.service.InvoiceService;
import com.example.tourmanagement.service.TourService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DailyTask {

    private final TourService tourService;

    private final InvoiceService invoiceService;

    public DailyTask(TourService tourService, InvoiceService invoiceService) {
        this.tourService = tourService;
        this.invoiceService = invoiceService;
    }
   // @Scheduled(fixedRate = 60000) // runs every 1 minute
    @Scheduled(cron = "0 0 0 * * *") // runs at 12:00 AM every day
    public void myTask() {
        System.out.println("Task is running...");

        List<Tour> tours= tourService.findToursByDayAndSmallerThanMinCapacity(LocalDate.now());

        for (Tour tour : tours) {
           // System.out.println("Tour: " + tour.getTourName() + " is not enough members");
            tour.setTourStatus("Canceled");
            tourService.saveTour(tour);
            List<Invoice> invoices = invoiceService.findInvoicesByTour(tour);
            for(Invoice invoice : invoices){
                invoice.setStatus(InvoiceStatus.CANCELLED);
                invoiceService.saveInvoice(invoice);
            }

        }

    }
}
