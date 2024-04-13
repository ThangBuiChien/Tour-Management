package com.example.tourmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Route {
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private long id;

    private String startLocation;

    private String endLocation;

    private int distance;

    @OneToMany(mappedBy = "route", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<DetailRoute> detailRouteList;

    public List<DetailRoute> getDetailRouteList() {
        return detailRouteList;
    }

    public void setDetailRouteList(List<DetailRoute> detailRouteList) {
        this.detailRouteList = detailRouteList;
    }

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


}
