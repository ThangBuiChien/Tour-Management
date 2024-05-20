package com.example.tourmanagement.service.impl;

import com.example.tourmanagement.model.Feedback;
import com.example.tourmanagement.repository.FeedbackRepo;
import com.example.tourmanagement.service.FeedbackService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepo repo;

    public FeedbackServiceImpl(FeedbackRepo repo){
        this.repo = repo;
    }

    @Override
    public List<Feedback> getAllFeedback() {
        return this.repo.findAll();
    }

    @Override
    public void saveFeedback(Feedback feedback) {
        this.repo.save(feedback);
    }

    @Override
    public Optional<Feedback> findByID(long id) {
        return this.repo.findById(id);
    }

    @Override
    public void deleteFeedback(long id) {
        this.repo.deleteById(id);

    }
    @Override
    public List<Feedback> findAllByTourId(long tourId) {
        return this.repo.findByTourId(tourId);  // Assuming this method exists in the repository
    }

}
