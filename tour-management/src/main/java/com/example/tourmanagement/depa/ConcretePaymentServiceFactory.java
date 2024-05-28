package com.example.tourmanagement.depa;

import org.springframework.stereotype.Service;

@Service
public class ConcretePaymentServiceFactory implements PaymentServiceFactory{
    @Override
    public PaymentService createPaymentService(String paymentMethod) {
        if ("bank".equalsIgnoreCase(paymentMethod)) {
            return new BankTransferPaymentService();
        } else if ("qr".equalsIgnoreCase(paymentMethod)) {
            return new QRTransferPaymentService();
        }else {
            throw new IllegalArgumentException("Invalid payment method: " + paymentMethod);
        }
    }
}
