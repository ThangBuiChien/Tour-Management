package com.example.tourmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Date;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long paymentAccount;

    private String payerName;

    private String amount;

    private Date paymentDate;

    private String description;


    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public long getPaymentAccount() { return paymentAccount; }

    public void setPaymentAccount(long paymentAccount) { this.paymentAccount = paymentAccount; }

    public String getPayerName() { return payerName; }

    public void setPayerName(String payerName) { this.payerName = payerName; }

    public String getAmount() { return amount; }

    public void setAmount(String amount) { this.amount = amount; }

    public Date getPaymentDate() { return paymentDate; }

    public void setPaymentDate(Date paymentDate) { this.paymentDate = paymentDate; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }


}
