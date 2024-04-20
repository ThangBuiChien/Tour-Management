package com.example.tourmanagement.service.impl;

import com.example.tourmanagement.model.UserRole;
import com.example.tourmanagement.repository.UserRoleRepo;
import com.example.tourmanagement.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepo repo;

    @Autowired
    public UserRoleServiceImpl(UserRoleRepo repo){
        this.repo = repo;
    }
    @Override
    public List<UserRole> getAllUserRole() {
        return repo.findAll();
    }

    @Override
    public void saveUserRole(UserRole userRole) {
        this.repo.save(userRole);
    }

    @Override
    public Optional<UserRole> findByID(long id) {
        return this.repo.findById(id);
    }

    @Override
    public void deleteUserRole(long id) {
        this.repo.deleteById(id);

    }
}
