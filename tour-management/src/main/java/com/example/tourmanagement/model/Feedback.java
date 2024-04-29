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
    private User user;

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

    public User getUser() {return user;}

    public void setUser(User user) {
        this.user = user;
    }

    public Tour getTour() {return tour;}

    public void setTour(Tour tour) {
        this.tour = tour;
    }
}
