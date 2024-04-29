package com.example.tourmanagement.model;

import jakarta.persistence.*;

@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private long id;

    private String comment;

    private int rating;

    @ManyToOne
    private UserModel userModel;

    @ManyToOne
    private Tour tour;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public UserModel getUserModel() {return userModel;}

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public Tour getTour() {return tour;}

    public void setTour(Tour tour) {
        this.tour = tour;
    }
}
