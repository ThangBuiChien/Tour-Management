package com.example.tourmanagement.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class UserRole {
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private long id;

    private String roleName;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
