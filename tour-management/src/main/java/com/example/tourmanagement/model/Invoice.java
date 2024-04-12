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
    private User user;

    @ManyToOne
    private Tour tour;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "listOfMembers", joinColumns = @JoinColumn(name = "invoice_id"))
    @Column(name = "listOfMember", nullable = false)
    private List<String> listOfMember = new ArrayList<>();

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
