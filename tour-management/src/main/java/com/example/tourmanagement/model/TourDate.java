package com.example.tourmanagement.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class TourDate {
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private long id;

    @Column(name = "start_date")
    LocalDate startDate;

    @Column(name = "end_date")
    LocalDate endDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
