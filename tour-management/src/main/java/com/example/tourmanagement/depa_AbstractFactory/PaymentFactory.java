package com.example.tourmanagement.depa_AbstractFactory;

import com.example.tourmanagement.depa.PaymentService;

public class PaymentFactory {
    public static PaymentService getPaymentService(PaymentAbstractFactory factory) {
        return factory.createPayment();
    }
}
