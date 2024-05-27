package com.example.tourmanagement.depa_AbstractFactory;

import com.example.tourmanagement.depa.BankTransferPaymentService;
import com.example.tourmanagement.depa.PaymentService;

public class BankTransferFactory implements PaymentAbstractFactory {
    @Override
    public PaymentService createPayment() {
        return new BankTransferPaymentService();
    }
}
