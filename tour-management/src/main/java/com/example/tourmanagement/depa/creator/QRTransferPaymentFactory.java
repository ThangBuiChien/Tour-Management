package com.example.tourmanagement.depa.creator;

import com.example.tourmanagement.depa.product.PaymentService;
import com.example.tourmanagement.depa.product.QRTransferPaymentService;

public class QRTransferPaymentFactory implements  PaymentServiceFactory {
    @Override
    public PaymentService createPaymentService(String paymentMethod) {
        if (paymentMethod.equals("qr")) {
            return new QRTransferPaymentService();
        }
        return null;
    }
}
