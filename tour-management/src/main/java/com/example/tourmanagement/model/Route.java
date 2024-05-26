package com.example.tourmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Route {
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private long id;

    private String startLocation;

    private String endLocation;

    private int distance;

    @OneToMany(mappedBy = "route", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<DetailRoute> detailRouteList;

}
