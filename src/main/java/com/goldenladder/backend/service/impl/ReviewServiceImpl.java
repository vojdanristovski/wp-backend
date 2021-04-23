package com.goldenladder.backend.service.impl;

import com.goldenladder.backend.model.Movie;
import com.goldenladder.backend.model.Review;
import com.goldenladder.backend.model.User;
import com.goldenladder.backend.model.exception.NotFoundException;
import com.goldenladder.backend.repository.MovieRepository;
import com.goldenladder.backend.repository.ReviewRepository;
import com.goldenladder.backend.repository.UserRepository;
import com.goldenladder.backend.service.ReviewService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    public ReviewServiceImpl(ReviewRepository reviewRepository, MovieRepository movieRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Optional<Review> createReview(Review review, Movie movie, User user) {

        movie.getReviews().add(review);
        movie.updateRating(review.getRating()); //updating the rating of the movie
        user.getRated().add(review);

        return Optional.of(this.reviewRepository.save(review));
    }
}
