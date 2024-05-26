package com.example.tourmanagement.service.impl;

import com.example.tourmanagement.model.Tour;
import com.example.tourmanagement.repository.TourRepo;
import com.example.tourmanagement.service.TourService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public Page<Tour> getAllTour(Pageable pageable, String keyword) {
        if (keyword != null) {
            return repo.searchPageable(pageable, keyword);
        }
        return repo.findAll(pageable);
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

    @Override
    public List<Tour> findToursByDayAndSmallerThanMinCapacity(LocalDate date) {
        return repo.findToursByDayAndSmallerThanMinCapacity(date);
    }

    @Override
    public Page<Tour> getTourByKeyWordAndDate(Pageable pageable, String keyword, LocalDate date) {
        if (keyword != null && date != null) {
            return repo.searchPageableAndDate(pageable, keyword, date);
        }
        else if(keyword != null){
            return repo.searchPageable(pageable, keyword);
        }
        else if(date != null){
            return repo.searchByDate(pageable, date);
        }
        return repo.findAll(pageable);
    }
    public Tour generateTourPrototype(Tour prototype) { //for depa
        return prototype.clone();
    }
}
