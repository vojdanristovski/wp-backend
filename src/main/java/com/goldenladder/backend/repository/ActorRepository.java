package com.goldenladder.backend.repository;

import com.goldenladder.backend.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor,String> {

//    List<Actor> findAllByMovies(List<ActorMovie> movies);

    List<Actor> findAllByNameContaining(String text);

}
