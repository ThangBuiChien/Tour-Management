package com.example.tourmanagement.repository;

import com.example.tourmanagement.model.TourAssignment;
import com.example.tourmanagement.model.TourGuide;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourAssignmentRepository extends JpaRepository<TourAssignment, Long> {

        List<TourAssignment> findByTourGuide(TourGuide tourGuide);
    }

