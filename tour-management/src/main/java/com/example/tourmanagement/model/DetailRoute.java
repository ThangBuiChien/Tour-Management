package com.example.tourmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DetailRoute {
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private long id;

    private int lengthTrip;

    private String stopLocation;

    private String detailTrip;

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

    public String getDetailTrip() {
        return detailTrip;
    }

    public void setDetailTrip(String detailTrip) {
        this.detailTrip = detailTrip;
    }
}
