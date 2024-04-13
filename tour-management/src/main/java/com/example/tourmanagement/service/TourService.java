package com.example.tourmanagement.service;

import com.example.tourmanagement.model.Tour;

import java.util.List;
import java.util.Optional;

public interface TourService {
    List<Tour> getAllTour();

    void saveTour(Tour route);

    Optional<Tour> findByID(long id);

    void deleteTour(long id);
}
