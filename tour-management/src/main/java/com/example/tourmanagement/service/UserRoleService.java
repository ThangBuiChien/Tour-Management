package com.example.tourmanagement.service;

import com.example.tourmanagement.model.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserRoleService {
    List<UserRole> getAllUserRole();

    void saveUserRole(UserRole userRole);

    Optional<UserRole> findByID(long id);

    void deleteUserRole(long id);
}
