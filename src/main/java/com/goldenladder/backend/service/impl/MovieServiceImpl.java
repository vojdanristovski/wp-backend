package com.goldenladder.backend.service.impl;

import com.goldenladder.backend.model.Actor;
import com.goldenladder.backend.model.Movie;
import com.goldenladder.backend.repository.MovieRepository;
import com.goldenladder.backend.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    @Override
    public List<Movie> findTop10Rated() {

        return this.movieRepository.findTopRated(PageRequest.of(0,10));

    }

    @Override
    public List<Movie> findNewest() {
        return this.movieRepository.findNewestMovies(PageRequest.of(0,10));
    }

    @Override
    public List<Movie> findTopByGenre(String genre) {
        return this.movieRepository.findAllByGenreSorted(genre,PageRequest.of(0,30));
    }

    @Override
    public List<Movie> findTopPopular() {
        return this.movieRepository.findTopPopularity(PageRequest.of(0,10));
    }

    @Override
    public List<Movie> search(String searchText) {
        return this.movieRepository.findAllByTitleContaining(searchText);
    }
}