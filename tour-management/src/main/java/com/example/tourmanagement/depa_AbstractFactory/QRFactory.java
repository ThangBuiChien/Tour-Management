package com.example.tourmanagement.depa_AbstractFactory;

import com.example.tourmanagement.depa.PaymentService;
import com.example.tourmanagement.depa.QRTransferPaymentService;

public class QRFactory implements  PaymentAbstractFactory{
    @Override
    public PaymentService createPayment() {
        return new QRTransferPaymentService();
    }
}
