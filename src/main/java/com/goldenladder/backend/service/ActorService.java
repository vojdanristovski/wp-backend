package com.goldenladder.backend.service;

import com.goldenladder.backend.model.Actor;

import java.util.Optional;

public interface ActorService {

    Optional<Actor> findById(String id);

//    List<Actor> findActorsByMovie(List<ActorMovie> movies);
}
