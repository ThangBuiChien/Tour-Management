package com.example.tourmanagement.service;

import com.example.tourmanagement.model.TourGuide;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface TourGuideService {
    List<TourGuide> getAllTourGuides();

    void saveTour(TourGuide route);

    Optional<TourGuide> findById(long id);


    void deleteTour(long id);
}
