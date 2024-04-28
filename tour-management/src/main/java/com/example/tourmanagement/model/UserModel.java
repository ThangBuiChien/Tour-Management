package com.example.tourmanagement.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class UserModel {
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private long id;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name = "birthDate")
    private LocalDate birthDate;

    @Column(name = "citizenID")
    private int citizenID;

    @Column(name = "phoneNumber")
    private int phoneNumber;

    private enumRole userRole;

    public enumRole getUserRole() {
        return userRole;
    }

    public void setUserRole(enumRole userRole) {
        this.userRole = userRole;
    }

    public long getId() {return id;}

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {return fullName;}

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getCitizenID() {
        return citizenID;
    }

    public void setCitizenID(int citizenID) {
        this.citizenID = citizenID;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
