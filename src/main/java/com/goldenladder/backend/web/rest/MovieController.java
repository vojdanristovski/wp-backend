package com.goldenladder.backend.web.rest;

import com.goldenladder.backend.model.Movie;
import com.goldenladder.backend.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/m")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> findById(@PathVariable String id){
        return this.movieService.findById(id)
                .map(movie->ResponseEntity.ok().body(movie))
                .orElseGet(()->ResponseEntity.notFound().build());
    }
}