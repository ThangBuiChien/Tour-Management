package com.example.tourmanagement.depa.creator;

import com.example.tourmanagement.depa.product.PaymentService;

public interface PaymentServiceFactory {
    PaymentService createPaymentService(String paymentMethod);

}
