package com.example.tourmanagement.depa_abstractFactory1;

import com.example.tourmanagement.depa.product.BankTransferPaymentService;
import com.example.tourmanagement.depa.product.PaymentService;

public class BankTransferFactory implements PaymentAbstractFactory {
    @Override
    public PaymentService createPayment() {
        return new BankTransferPaymentService();
    }
}
