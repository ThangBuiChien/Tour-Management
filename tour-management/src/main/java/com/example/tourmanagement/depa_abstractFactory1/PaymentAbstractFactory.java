package com.example.tourmanagement.depa_abstractFactory1;

import com.example.tourmanagement.depa.product.PaymentService;

public interface PaymentAbstractFactory {
    public PaymentService createPayment();

}
