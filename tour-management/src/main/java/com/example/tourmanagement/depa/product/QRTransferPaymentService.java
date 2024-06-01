package com.example.tourmanagement.depa.product;

public class QRTransferPaymentService implements PaymentService{
    @Override
    public String processPayment(double amount) {
        System.out.println("Processing QR code payment of $" + amount);
        return "Processing QR payment of $" + amount;


    }
}
