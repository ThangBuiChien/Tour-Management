package com.example.tourmanagement.depa_abstractFactory1;

import com.example.tourmanagement.depa.product.PaymentService;
import com.example.tourmanagement.depa.product.QRTransferPaymentService;
import com.example.tourmanagement.depa_abstractFactory1.PaymentAbstractFactory1;

public class QRFactory implements PaymentAbstractFactory {
    @Override
    public PaymentService createPayment() {
        return new QRTransferPaymentService();
    }
}

