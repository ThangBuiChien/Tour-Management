package com.example.tourmanagement.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class UserRole {
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private long id;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "role")
    private String role;
    @OneToMany(mappedBy = "user_role", cascade = CascadeType.REMOVE)
    private List<User> usersList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<User> usersList) {
        this.usersList = usersList;
}
