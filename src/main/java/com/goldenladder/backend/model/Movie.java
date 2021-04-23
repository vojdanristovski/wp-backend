package com.goldenladder.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="movies")
public class Movie {

    @Id
    @Column(name="imdb_id")
    private String movieId;

    @Column(name="title")
    private String title;

    @Column(name="original_title")
    private String originalTitle;

    @Column(name="date_published")
    private String datePublished;

    @Column(name="genre")
    private String genre;

    @Column(name="duration")
    private Integer duration;

    @Column(name="country")
    private String country;

    @Column(name = "language")
    private String language;

    @Column(name="production_company")
    private String productionCompany;

    @Column(name="description")
    private String description;

    @Column(name = "avg_vote")
    private Double avgVote;

    @Column(name="votes")
    private Integer voteCount;

    @OneToMany(mappedBy = "movie")

    private Set<Review> reviews;

    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private List<ActorMovie> actors;

    public Movie() {

    }
    public Movie(String title, String datePublished, String genre, Integer duration, String country, String language, String productionCompany, String description) {
        this.title = title;
        this.datePublished = datePublished;
        this.genre = genre;
        this.duration = duration;
        this.country = country;
        this.language = language;
        this.productionCompany = productionCompany;
        this.description = description;
    }

    public void updateRating(Integer newRating)
    {
        voteCount++;

        avgVote = (this.avgVote * (voteCount - 1) + newRating) /voteCount;

    }


}
