package com.example.tourmanagement.service;

import com.example.tourmanagement.model.TourAssignment;
import com.example.tourmanagement.model.TourGuide;
import java.util.List;

public interface TourAssignmentService {
    List<TourAssignment> getAllTourAssignment();
    List<TourAssignment> findByTourGuide(TourGuide tourGuide);
    void save(TourAssignment tourAssignment);
}
