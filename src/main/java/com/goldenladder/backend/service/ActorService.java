package com.goldenladder.backend.service;

import com.goldenladder.backend.model.Actor;
import com.goldenladder.backend.model.ActorMovie;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

public interface ActorService {

    Optional<Actor> findById(String id);

//    List<Actor> findActorsByMovie(List<ActorMovie> movies);
}
