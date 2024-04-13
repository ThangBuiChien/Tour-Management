package com.example.tourmanagement.repository;

import com.example.tourmanagement.model.Capacity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapacityRepo extends JpaRepository<Capacity, Long> {
}
