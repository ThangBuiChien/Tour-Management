package com.example.tourmanagement.depa;

public class BankTransferPaymentService implements PaymentService{
    @Override
    public String processPayment(double amount) {
        System.out.println("Processing Bank transfer payment of $" + amount);
        return "Processing Bank transfer payment of $" + amount;

    }
}
