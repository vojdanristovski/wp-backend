package com.goldenladder.backend.service;

import com.goldenladder.backend.model.Movie;
import com.goldenladder.backend.model.Review;
import com.goldenladder.backend.model.User;

import java.util.Optional;

public interface ReviewService {

    Optional<Review> createReview(Review review, Movie movie, User user);
    Optional<Review> getRating(User user,Movie movie);
}
