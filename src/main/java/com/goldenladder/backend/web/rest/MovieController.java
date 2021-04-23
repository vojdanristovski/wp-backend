package com.goldenladder.backend.web.rest;

import com.goldenladder.backend.model.*;
import com.goldenladder.backend.model.exception.NotFoundException;
import com.goldenladder.backend.service.ActorService;
import com.goldenladder.backend.service.MovieService;
import com.goldenladder.backend.service.ReviewService;
import com.goldenladder.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/m")
public class MovieController {

    private final MovieService movieService;
    private final UserService userService;
    private final ReviewService reviewService;
    private final ActorService actorService;

    public MovieController(MovieService movieService, UserService userService, ReviewService reviewService, ActorService actorService) {
        this.movieService = movieService;
        this.userService = userService;
        this.reviewService = reviewService;
        this.actorService = actorService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> findById(@PathVariable String id){
        return this.movieService.findById(id)
                .map(movie->ResponseEntity.ok().body(movie))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/rate")
    public ResponseEntity<Review> rateMovie(@PathVariable String id,
                          @RequestParam Integer rating,
                          @RequestParam String comment)
    {
        String username = UserController.getUsername(); // getting the username

        Movie movie = this.movieService.findById(id)
                .orElseThrow(NotFoundException::new);

        User user = this.userService.loadUserByUsername(username);

        Review review = new Review(user,movie,rating,comment); //making new review

       return this.reviewService.createReview(review,movie,user)
               .map(review1 -> ResponseEntity.ok().body(review1))
               .orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping("/g/{genre}")
    public List<Movie> findByGenre(@PathVariable String genre)
    {
        return this.movieService.findTopByGenre(genre);
    }

    @GetMapping("/topRated")
    public List<Movie> getTopRated()
    {
        return this.movieService.findTop10Rated();
    }

    @GetMapping("/newest")
    public List<Movie> getNewest()
    {
        return this.movieService.findNewest();
    }

    @GetMapping("/popular")
    public List<Movie> getPopular(){
        return this.movieService.findTopPopular();
    }


    @GetMapping("/{id}/watchlist")
    public void addToWatchlist(@PathVariable String id)
    {
        String username = UserController.getUsername();

        User u = this.userService.loadUserByUsername(username);

        Movie m = this.movieService.findById(id)
                .orElseThrow(NotFoundException::new);

        u.getWatchlist().add(m);
    }
}