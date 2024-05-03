package com.example.tourmanagement.repository;

import com.example.tourmanagement.model.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TourRepo extends JpaRepository<Tour, Long> {
    @Query("SELECT p FROM Tour p WHERE CONCAT(p.detailRoute, ' ', p.tourDescription, ' ',p.tourName) LIKE %?1%")
    public Page<Tour> searchPageable(Pageable pageable, String keyword);

    @Query("SELECT t FROM Tour t WHERE t.startDate = :currentDate AND t.register < t.tourCapacity.minMember")
    public List<Tour> findToursByDayAndSmallerThanMinCapacity(LocalDate currentDate);
}
