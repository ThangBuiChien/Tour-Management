package com.example.tourmanagement.repository;
import com.example.tourmanagement.model.TourGuide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourGuideRepository extends JpaRepository<TourGuide, Long> {
}
