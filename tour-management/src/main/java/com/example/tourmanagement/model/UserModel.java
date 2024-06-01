package com.example.tourmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserModel {
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private long id;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "email", unique=true)
    private String email;

    @Column(name="password")
    private String password;

    @Column(name = "birthDate")
    private LocalDate birthDate;

    @Column(name = "citizenID")
    private int citizenID;

    @Column(name = "phoneNumber")
    private int phoneNumber;

    @Column(name = "role")
    private enumRole userRole;



}
