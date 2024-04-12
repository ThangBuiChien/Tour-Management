package com.example.tourmanagement.model;

import jakarta.persistence.*;

@Entity
public class Route {
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private long id;

    private String startLocation;

    private String endLocation;

    private int distance;

    @ManyToOne
    private DetailRoute detailRoute;




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public DetailRoute getDetailRoute() {
        return detailRoute;
    }

    public void setDetailRoute(DetailRoute detailRoute) {
        this.detailRoute = detailRoute;
    }
}
