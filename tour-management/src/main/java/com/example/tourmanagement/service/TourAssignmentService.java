package com.example.tourmanagement.service;

import com.example.tourmanagement.model.TourAssignment;

import java.util.List;

public interface TourAssignmentService {
    List<TourAssignment> getAllTourAssignment();
    void save(TourAssignment tourAssignment);
}
