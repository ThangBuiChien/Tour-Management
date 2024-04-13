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
    private int lengthTrip;
    private String tourDescription;

    private float tourPrice;


    //ignore the error


//    @OneToMany(mappedBy = "tour")
//    private List<User> tourMembers;
//
//  //  private final List<User> tourMember = new ArrayList<User>();
//
//
//    public List<User> getTourMembers() {
//        return tourMembers;
//    }
//
//    public void setTourMembers(List<User> tourMembers) {
//        this.tourMembers = tourMembers;
//    }

    @ManyToOne
    private DetailRoute detailRoute;
//    @ManyToOne
//    private TourDate tourDate;


    public DetailRoute getDetailRoute() {
        return detailRoute;
    }

    public void setDetailRoute(DetailRoute detailRoute) {
        this.detailRoute = detailRoute;
    }

    private LocalDate startDate;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @ManyToOne
    private Capacity tourCapacity;

    private String tourStatus;

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

    public String getTourStatus() {
        return tourStatus;
    }

    public void setTourStatus(String tourStatus) {
        this.tourStatus = tourStatus;
    }
}
