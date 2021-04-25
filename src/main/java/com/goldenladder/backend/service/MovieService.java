package com.goldenladder.backend.service;

import com.goldenladder.backend.model.Movie;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface MovieService {

    Optional<Movie> findById(String id);

    List<Movie> findTop10Rated();

    List<Movie> findNewest();

    List<Movie> findTopByGenre(String genre);

    List<Movie> findTopPopular();

    List<Movie> search(String searchText);
}
