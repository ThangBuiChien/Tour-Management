package com.example.tourmanagement.repository;

import com.example.tourmanagement.model.DetailRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailRouteRepo extends JpaRepository<DetailRoute, Long> {
}
