package com.goldenladder.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="username")
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name="imdb_id")
    @JsonBackReference
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

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Review review = (Review) o;
//        return Objects.equals(id, review.id) && Objects.equals(user, review.user) && Objects.equals(movie, review.movie) && Objects.equals(rating, review.rating) && Objects.equals(comment, review.comment);
//    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rating, comment);
    }
}