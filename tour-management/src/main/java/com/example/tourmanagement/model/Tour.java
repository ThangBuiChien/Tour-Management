package com.example.tourmanagement.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tour {

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private long id;

    private String tourName;
    @ManyToOne
    private DetailRoute detailRoute;
    private int lengthTrip;
    private String tourDescription;

    private float tourPrice;
    
    @ManyToOne
    private Capacity tourCapacity;

    private LocalDate startDate;

    private String tourStatus;
    

    //current total of customer
    private int register;

    @ManyToOne
    private TourGuide tourGuide;

    public TourGuide getTourGuide() {
        return tourGuide;
    }

    public void setTourGuide(TourGuide tourGuide) {
        this.tourGuide = tourGuide;
    }
    //
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public DetailRoute getDetailRoute() {
        return detailRoute;
    }

    public void setDetailRoute(DetailRoute detailRoute) {
        this.detailRoute = detailRoute;
    }

    public int getLengthTrip() {
        return lengthTrip;
    }

    public void setLengthTrip(int lengthTrip) {
        this.lengthTrip = lengthTrip;
    }

    public String getTourDescription() {
        return tourDescription;
    }

    public void setTourDescription(String tourDescription) {
        this.tourDescription = tourDescription;
    }

    public float getTourPrice() {
        return tourPrice;
    }

    public void setTourPrice(float tourPrice) {
        this.tourPrice = tourPrice;
    }

    public Capacity getTourCapacity() {
        return tourCapacity;
    }

    public void setTourCapacity(Capacity tourCapacity) {
        this.tourCapacity = tourCapacity;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getTourStatus() {
        return tourStatus;
    }

    public void setTourStatus(String tourStatus) {
        this.tourStatus = tourStatus;
    }

    public int getRegister() {
        return register;
    }

    public void setRegister(int register) {
        this.register = register;
    }
    public int getRemainingCapacity() {
        return this.tourCapacity.getMaxMember() - this.register;
    }

}
