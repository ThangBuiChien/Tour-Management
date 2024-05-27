package com.example.tourmanagement.depa_AbstractFactory;

import com.example.tourmanagement.depa.PaymentService;

public interface PaymentAbstractFactory {
    public PaymentService createPayment();
}
