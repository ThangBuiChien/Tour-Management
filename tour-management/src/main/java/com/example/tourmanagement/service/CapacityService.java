package com.example.tourmanagement.service;

import com.example.tourmanagement.model.Capacity;

import java.util.List;
import java.util.Optional;

public interface CapacityService {
    List<Capacity> getAllCapacity();

    void saveCapacity(Capacity capacity);

    Optional<Capacity> findByID(long id);

    void deleteCapacity(long id);
}
