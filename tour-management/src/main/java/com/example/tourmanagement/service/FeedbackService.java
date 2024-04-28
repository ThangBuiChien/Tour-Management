package com.example.tourmanagement.service;

import com.example.tourmanagement.model.Feedback;

import java.util.List;
import java.util.Optional;

public interface FeedbackService {
    List<Feedback> getAllFeedback();

    void saveFeedback(Feedback feedback);

    Optional<Feedback> findByID(long id);

    void deleteFeedback(long id);
}
