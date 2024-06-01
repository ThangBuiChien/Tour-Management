package com.example.tourmanagement.depa.creator;

import com.example.tourmanagement.depa.product.BankTransferPaymentService;
import com.example.tourmanagement.depa.product.PaymentService;

public class BankTransferPaymentFactory implements PaymentServiceFactory {
    @Override
    public PaymentService createPaymentService(String paymentMethod) {
        if (paymentMethod.equals("bank")) {
            return new BankTransferPaymentService();
        }
        return null;
    }
}
