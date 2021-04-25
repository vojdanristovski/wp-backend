package com.goldenladder.backend.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class ActorMovieKey implements Serializable {

    @Column(name="tmdb_movie_id")
    private String movieId;

    @Column(name="tmdb_actor_id")
    private String actorId;

    public ActorMovieKey() {
    }

    public ActorMovieKey(String movieId, String actorId) {
        this.movieId = movieId;
        this.actorId = actorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActorMovieKey that = (ActorMovieKey) o;
        return Objects.equals(movieId, that.movieId) && Objects.equals(actorId, that.actorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, actorId);
    }
}
