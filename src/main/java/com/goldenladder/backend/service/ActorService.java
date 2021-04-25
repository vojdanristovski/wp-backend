package com.goldenladder.backend.service;

import com.goldenladder.backend.model.Actor;
import com.goldenladder.backend.model.Movie;

import java.util.List;
import java.util.Optional;

public interface ActorService {

    Optional<Actor> findById(String id);

    //    List<Actor> findActorsByMovie(List<ActorMovie> movies);

    List<Actor> search(String searchText);
}
