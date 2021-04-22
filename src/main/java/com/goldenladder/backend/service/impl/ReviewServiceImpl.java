package com.goldenladder.backend.service.impl;

import com.goldenladder.backend.model.Review;
import com.goldenladder.backend.repository.ReviewRepository;
import com.goldenladder.backend.service.ReviewService;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review createReview(Review review) {
        return this.reviewRepository.save(review);
    }
}
