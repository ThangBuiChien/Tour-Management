package com.example.tourmanagement.service.impl;

import com.example.tourmanagement.model.TourAssignment;
import com.example.tourmanagement.repository.TourAssignmentRepository;
import com.example.tourmanagement.service.TourAssignmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourAssignmentImpl implements TourAssignmentService {

    private TourAssignmentRepository repo;

    public TourAssignmentImpl(TourAssignmentRepository repo){
        this.repo = repo;

    }
    @Override
    public List<TourAssignment> getAllTourAssignment() {
        return repo.findAll();
    }

    @Override
    public void save(TourAssignment tourAssignment) {
        repo.save(tourAssignment);
    }
}
