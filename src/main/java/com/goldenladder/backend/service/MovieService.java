package com.goldenladder.backend.service;

import com.goldenladder.backend.model.Movie;

import java.util.Optional;

public interface MovieService {
    Optional<Movie> findById(String id);
}
