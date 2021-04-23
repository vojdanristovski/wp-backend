package com.goldenladder.backend.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="username")
    private User user;

    @ManyToOne
    @JoinColumn(name="imdb_id")
    private Movie movie;

    private Integer rating;

    private String comment;

    public Review() {
    }

    public Review(User user, Movie titles, Integer rating, String comment) {
        this.user = user;
        this.movie = titles;
        this.rating = rating;
        this.comment = comment;
    }
}