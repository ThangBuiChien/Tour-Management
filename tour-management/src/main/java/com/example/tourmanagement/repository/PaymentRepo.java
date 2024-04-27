package com.example.tourmanagement.repository;

import com.example.tourmanagement.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<Payment, Long> {
}
