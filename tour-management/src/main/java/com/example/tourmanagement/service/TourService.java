package com.example.tourmanagement.service;

import com.example.tourmanagement.model.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TourService {
    List<Tour> getAllTour();

    Page<Tour> getAllTour(Pageable pageable, String keyword);


    void saveTour(Tour route);

    Optional<Tour> findByID(long id);

    void deleteTour(long id);
}
