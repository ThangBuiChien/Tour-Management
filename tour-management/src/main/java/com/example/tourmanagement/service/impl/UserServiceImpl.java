package com.example.tourmanagement.service.impl;

import com.example.tourmanagement.model.UserModel;
import com.example.tourmanagement.model.enumRole;
import com.example.tourmanagement.repository.UserRepo;
import com.example.tourmanagement.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo repo;


    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepo repo, PasswordEncoder passwordEncoder){
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public List<UserModel> getAllUser() {
        return this.repo.findAll();
    }

    @Override
    public void saveUser(UserModel userModel) {
        userModel.setFullName(userModel.getEmail());
        userModel.setUserRole(enumRole.USER);
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        this.repo.save(userModel);
    }

    @Override
    public Optional<UserModel> findByID(long id) {
        return this.repo.findById(id);
    }

    @Override
    public void deleteUser(long id) {
        this.repo.deleteById(id);

    }

    @Override
    public Optional<UserModel> loadByEmail(String email) {
        return repo.findByEmail(email);
    }


}
