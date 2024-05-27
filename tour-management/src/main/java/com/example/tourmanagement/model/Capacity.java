package com.example.tourmanagement.model;

import jakarta.persistence.*;

@Entity
public class Capacity implements Cloneable{
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private long id;

    @Column(name = "min_member")
    int minMember;
    @Column(name = "max_member")
    int maxMember;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMinMember() {
        return minMember;
    }

    public void setMinMember(int minMember) {
        this.minMember = minMember;
    }

    public int getMaxMember() {
        return maxMember;
    }

    public void setMaxMember(int maxMember) {
        this.maxMember = maxMember;
    }

    @Override
    public Capacity clone() {
        try {
            return (Capacity) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning not supported", e);
        }
    }
}
