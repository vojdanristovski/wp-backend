package com.goldenladder.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@Table(name="actor_movie")
public class ActorMovie {
    @EmbeddedId
    private ActorMovieKey pk;


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("movieId")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("actorId")
    private Actor actor;

    @Column(name="category")
    private String category;

    public ActorMovie() {
    }

    public ActorMovie(Movie movie, Actor actor, String category) {
        this.movie = movie;
        this.actor = actor;
        this.category = category;
        this.pk = new ActorMovieKey(movie.getMovieId(),actor.getActorId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActorMovie that = (ActorMovie) o;
        return Objects.equals(movie, that.movie) && Objects.equals(actor, that.actor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie, actor);
    }
}
