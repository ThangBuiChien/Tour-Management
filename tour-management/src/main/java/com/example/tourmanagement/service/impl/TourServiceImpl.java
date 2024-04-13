package com.example.tourmanagement.service.impl;

import com.example.tourmanagement.model.Tour;
import com.example.tourmanagement.repository.TourRepo;
import com.example.tourmanagement.service.TourService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TourServiceImpl implements TourService {
    private final TourRepo repo;

    public  TourServiceImpl(TourRepo repo){
        this.repo = repo;
    }


    @Override
    public List<Tour> getAllTour() {
        return repo.findAll()   ;
    }

    @Override
    public void saveTour(Tour tour) {
        this.repo.save(tour);
    }

    @Override
    public Optional<Tour> findByID(long id) {
        return this.repo.findById(id);
    }

    @Override
    public void deleteTour(long id) {
        this.repo.deleteById(id);

    }
}