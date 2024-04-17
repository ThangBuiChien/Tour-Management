package com.example.tourmanagement.model;

import jakarta.persistence.*;

@Entity
public class DetailRoute {
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private long id;

    private int lengthTrip;

    private String stopLocation;

    @Column(columnDefinition = "TEXT")
    private String shortDetailTrip;
    @Column(columnDefinition = "TEXT")
    private String detailTrip;

    @ManyToOne
    @JoinColumn(name = "route_id")

    private Route route;

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getLengthTrip() {
        return lengthTrip;
    }

    public void setLengthTrip(int lengthTrip) {
        this.lengthTrip = lengthTrip;
    }

    public String getStopLocation() {
        return stopLocation;
    }

    public void setStopLocation(String stopLocation) {
        this.stopLocation = stopLocation;
    }

    public String getShortDetailTrip() {
        return shortDetailTrip;
    }

    public void setShortDetailTrip(String shortDetailTrip) {
        this.shortDetailTrip = shortDetailTrip;
    }

    public String getDetailTrip() {
        return detailTrip;
    }

    public void setDetailTrip(String detailTrip) {
        this.detailTrip = detailTrip;
    }
}
