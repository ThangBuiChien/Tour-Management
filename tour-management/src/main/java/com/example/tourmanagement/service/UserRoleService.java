package com.example.tourmanagement.service;

import com.example.tourmanagement.model.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserRoleService {
    List<User> getAllUser();

    void saveUser(User user);

    Optional<User> findByID(long id);

    void deleteUser(long id);
}
