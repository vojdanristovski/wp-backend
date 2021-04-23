package com.goldenladder.backend.service.impl;

import com.goldenladder.backend.model.Actor;
import com.goldenladder.backend.model.ActorMovie;
import com.goldenladder.backend.repository.ActorRepository;
import com.goldenladder.backend.service.ActorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService {
    private final ActorRepository actorRepository;

    public ActorServiceImpl(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @Override
    public Optional<Actor> findById(String id) {
        return this.actorRepository.findById(id);
    }

//    @Override
//    public List<Actor> findActorsByMovie(List<ActorMovie> movies) {
//        return this.actorRepository.findAllByMovies(movies);
//    }


}
