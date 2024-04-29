package com.example.tourmanagement.service;

import com.example.tourmanagement.model.Payment;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    Payment savePayment(Payment payment);
    Payment findPaymentById(Long id);

}
