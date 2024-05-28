package com.example.tourmanagement.depa;

public interface PaymentServiceFactory {
    PaymentService createPaymentService(String paymentMethod);

}
