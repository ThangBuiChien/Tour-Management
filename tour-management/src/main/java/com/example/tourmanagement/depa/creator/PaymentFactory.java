package com.example.tourmanagement.depa.creator;

public class PaymentFactory {
    public static PaymentServiceFactory getFactory(String paymentMethod) {
        switch (paymentMethod.toLowerCase()) {
            case "qr":
                return new QRTransferPaymentFactory();
            case "bank":
                return new BankTransferPaymentFactory();
            default:
                throw new IllegalArgumentException("Invalid payment method: " + paymentMethod);
        }
    }
}
