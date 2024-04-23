package com.example.tourmanagement.repository;

import com.example.tourmanagement.model.TourAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourAssignmentRepository extends JpaRepository<TourAssignment, Long> {
}
