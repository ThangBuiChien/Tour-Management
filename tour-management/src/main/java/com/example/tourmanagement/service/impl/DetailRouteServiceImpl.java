package com.example.tourmanagement.service.impl;

import com.example.tourmanagement.model.DetailRoute;
import com.example.tourmanagement.repository.DetailRouteRepo;
import com.example.tourmanagement.service.DetailRouteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailRouteServiceImpl implements DetailRouteService {

    private final DetailRouteRepo repo;

    public DetailRouteServiceImpl(DetailRouteRepo repo){
        this.repo = repo;
    }
    @Override
    public List<DetailRoute> getAllDetailRoute() {
        return this.repo.findAll();
    }

    @Override
    public void saveDetailRoute(DetailRoute detailRoute) {
         this.repo.save(detailRoute);
    }

    @Override
    public Optional<DetailRoute> findByID(long id) {
        return this.repo.findById(id);
    }

    @Override
    public void deleteDetailRoute(long id) {
        this.repo.deleteById(id);

    }
}
