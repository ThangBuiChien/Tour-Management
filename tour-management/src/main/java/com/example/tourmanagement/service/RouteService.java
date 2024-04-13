package com.example.tourmanagement.service;

import com.example.tourmanagement.model.Route;

import java.util.List;
import java.util.Optional;


public interface RouteService {
    List<Route> getAllRoute();

    void saveRoute(Route route);

    Optional<Route> findByID(long id);

    void deleteRoute(long id);
}
