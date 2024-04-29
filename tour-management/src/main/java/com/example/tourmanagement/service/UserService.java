package com.example.tourmanagement.service;

import com.example.tourmanagement.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserModel> getAllUser();

    void saveUser(UserModel userModel);

    Optional<UserModel> findByID(long id);

    void deleteUser(long id);

    Optional<UserModel> loadByEmail(String email);


}
