package com.goldenladder.backend.repository;

import com.goldenladder.backend.model.ActorMovie;
import com.goldenladder.backend.model.ActorMovieKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorMovieRepository extends JpaRepository<ActorMovie, ActorMovieKey> {

}
