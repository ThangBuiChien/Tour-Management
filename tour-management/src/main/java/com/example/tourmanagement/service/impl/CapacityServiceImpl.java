package com.example.tourmanagement.service.impl;

import com.example.tourmanagement.model.Capacity;
import com.example.tourmanagement.repository.CapacityRepo;
import com.example.tourmanagement.service.CapacityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CapacityServiceImpl implements CapacityService {
    private final CapacityRepo repo;

    public CapacityServiceImpl(CapacityRepo repo){
        this.repo = repo;
    }


    @Override
    public List<Capacity> getAllCapacity() {
        return repo.findAll();
    }

    @Override
    public void saveCapacity(Capacity capacity) {
        this.repo.save(capacity);
    }

    @Override
    public Optional<Capacity> findByID(long id) {
        return this.repo.findById(id);
    }

    @Override
    public void deleteCapacity(long id) {
        this.repo.deleteById(id);
    }
}
