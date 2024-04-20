package com.example.tourmanagement.service.impl;

import com.example.tourmanagement.model.User;
import com.example.tourmanagement.repository.UserRepo;
import com.example.tourmanagement.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo repo;

    public UserServiceImpl(UserRepo repo){
        this.repo = repo;
    }


    @Override
    public List<User> getAllUser() {
        return this.repo.findAll();
    }

    @Override
    public void saveUser(User user) {
        this.repo.save(user);
    }

    @Override
    public Optional<User> findByID(long id) {
        return this.repo.findById(id);
    }

    @Override
    public void deleteUser(long id) {
        this.repo.deleteById(id);

    }
}
