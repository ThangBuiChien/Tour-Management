package com.example.tourmanagement.service;

import com.example.tourmanagement.model.DetailRoute;

import java.util.List;
import java.util.Optional;

public interface DetailRouteService {

    List<DetailRoute> getAllDetailRoute();

    void saveDetailRoute(DetailRoute detailRoute);

    Optional<DetailRoute> findByID(long id);

    void deleteDetailRoute(long id);

}
