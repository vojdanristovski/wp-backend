package com.goldenladder.backend.web.rest;

import com.goldenladder.backend.model.Movie;
import com.goldenladder.backend.model.Review;
import com.goldenladder.backend.model.User;
import com.goldenladder.backend.model.exception.NotFoundException;
import com.goldenladder.backend.service.MovieService;
import com.goldenladder.backend.service.ReviewService;
import com.goldenladder.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/m")
public class MovieController {

    private final MovieService movieService;
    private final UserService userService;
    private final ReviewService reviewService;

    public MovieController(MovieService movieService, UserService userService, ReviewService reviewService) {
        this.movieService = movieService;
        this.userService = userService;
        this.reviewService = reviewService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> findById(@PathVariable String id){
        return this.movieService.findById(id)
                .map(movie->ResponseEntity.ok().body(movie))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/rate")
    public void rateMovie(@PathVariable String id,
                          @RequestParam Integer rating,
                          @RequestParam String comment)
    {
        String username = UserController.getUsername(); // getting the username

        User u = userService.loadUserByUsername(username); // getting the user

        Movie movie = movieService.findById(id).orElseThrow(NotFoundException::new); // getting the movie

        Review review = new Review(u,movie,rating,comment); //making new review

        this.reviewService.createReview(review); //adding to db

        movie.getReviews().add(review);
        u.getRated().add(review);
    }
}