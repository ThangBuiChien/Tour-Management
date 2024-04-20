package com.example.tourmanagement.service.impl;

import java.util.List;
import java.util.Optional;

import com.example.tourmanagement.model.TourGuide;
import com.example.tourmanagement.repository.TourGuideRepository;
import com.example.tourmanagement.service.TourGuideService;
import org.springframework.stereotype.Service;

@Service
public class TourGuideServiceImpl implements TourGuideService{
    private final TourGuideRepository repo;

    public TourGuideServiceImpl(TourGuideRepository repo){
        this.repo = repo;
    }
    @Override
    public List<TourGuide> getAllTourGuides() {
        return repo.findAll();
    }

    @Override
    public void saveTour(TourGuide route) {
       repo.save(route);
    }

    @Override
    public Optional<TourGuide> findById(long id) {
        return repo.findById(id);
    }

    @Override
    public void deleteTour(long id) {
        repo.deleteById(id);
    }
    
}
