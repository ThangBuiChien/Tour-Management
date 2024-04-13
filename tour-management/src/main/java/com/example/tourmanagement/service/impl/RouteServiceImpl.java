package com.example.tourmanagement.service.impl;

import com.example.tourmanagement.model.Route;
import com.example.tourmanagement.repository.RouteRepo;
import com.example.tourmanagement.service.RouteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteRepo repo;

    public RouteServiceImpl(RouteRepo repo){
        this.repo = repo;
    }


    @Override
    public List<Route> getAllRoute() {
        return this.repo.findAll();
    }

    @Override
    public void saveRoute(Route route) {
        this.repo.save(route);
    }

    @Override
    public Optional<Route> findByID(long id) {
        return this.repo.findById(id);
    }

    @Override
    public void deleteRoute(long id) {
        this.repo.deleteById(id);

    }
}
