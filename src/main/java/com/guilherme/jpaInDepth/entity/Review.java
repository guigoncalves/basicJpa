package com.guilherme.jpaInDepth.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Review {

    @Id
    @GeneratedValue
    private Integer id;

    @Enumerated(EnumType.STRING)
    private ReviewRating rate;

    private String description;

    //By Default EAGER Fetch
    @ManyToOne
    @NotNull
    private Course course;

    public Review() { }


    public Review(ReviewRating rate, String description) {
        this.rate = rate;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }


    public ReviewRating getRate() {
        return rate;
    }

    public void setRate(ReviewRating rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Review{" +
                "rate='" + rate +
                "description='" + description+ '\'' +
                '}';
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
