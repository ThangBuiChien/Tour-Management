package com.example.tourmanagement.depa_abstractFactory1;

import com.example.tourmanagement.depa.product.PaymentService;
import com.example.tourmanagement.depa_abstractFactory1.PaymentAbstractFactory1;

public class PaymentAbstractFactory1 {
    public static PaymentService getPaymentService(PaymentAbstractFactory factory) {
        return factory.createPayment();
    }
}
