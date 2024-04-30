package com.example.tourmanagement.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private long id;

    private float totalPrice;

    private LocalDate date;

    @ManyToOne
    private UserModel userModel;

    @ManyToOne
    private Tour tour;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "listOfMembers", joinColumns = @JoinColumn(name = "invoice_id"))
    @Column(name = "listOfMember", nullable = false)
    private List<String> listOfMember = new ArrayList<>();

    private String paymentAccount;
    private String payerName;
    private LocalDate paymentDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public List<String> getListOfMember() {
        return listOfMember;
    }

    public void setListOfMember(List<String> listOfMember) {
        this.listOfMember = listOfMember;
    }

    public String getPaymentAccount() {
        return paymentAccount;
    }

    public void setPaymentAccount(String paymentAccount) {
        this.paymentAccount = paymentAccount;
    }
    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }
    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;

    public InvoiceStatus getStatus() {
        return status;
    }

    public void setStatus(InvoiceStatus status) {
        this.status = status;
    }
    @ManyToOne
    private UserModel user; // Make sure it's named 'user' or adjust your template accordingly

    // Getters and Setters
    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }


}
