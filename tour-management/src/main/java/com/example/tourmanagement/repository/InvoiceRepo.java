package com.example.tourmanagement.repository;

import com.example.tourmanagement.model.Invoice;
import com.example.tourmanagement.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepo extends JpaRepository<Invoice, Long> {
    List<Invoice> findByTour(Tour tour);
}
