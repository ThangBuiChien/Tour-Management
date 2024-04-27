package com.example.tourmanagement.service.impl;

import com.example.tourmanagement.model.Payment;
import com.example.tourmanagement.repository.PaymentRepo;
import com.example.tourmanagement.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepo paymentRepository;

    public PaymentServiceImpl(PaymentRepo paymentRepository) {this.paymentRepository = paymentRepository;}

    @Override
    @Transactional
    public Payment savePayment(Payment payment) {return paymentRepository.save(payment);}

    @Override
    @Transactional
    public Payment findPaymentById(Long id) {
        return paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment not found!"));
    }
}
