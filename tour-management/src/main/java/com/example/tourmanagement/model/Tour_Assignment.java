package com.example.tourmanagement.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Tour_Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id", nullable = false)
    private Tour tour;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_guide_id", nullable = false)
    private TourGuide tourGuide;

    // Constructors
    public Tour_Assignment() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public TourGuide getTourGuide() {
        return tourGuide;
    }

    public void setTourGuide(TourGuide tourGuide) {
        this.tourGuide = tourGuide;
    }
}