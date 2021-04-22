package com.goldenladder.backend.service.impl;

import com.goldenladder.backend.model.Movie;
import com.goldenladder.backend.repository.MovieRepository;
import com.goldenladder.backend.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    @Override
    public Optional<Movie> findById(String id) {
        return this.movieRepository.findById(id);
    }
}