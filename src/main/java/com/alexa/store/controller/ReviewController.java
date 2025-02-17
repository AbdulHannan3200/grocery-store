package com.alexa.store.controller;

import com.alexa.store.dto.ReviewDto;
import com.alexa.store.entity.Review;
import com.alexa.store.service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewsService reviewsService;

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody ReviewDto reviewDto) { // Use the DTO
        Review review = reviewsService.createReview(reviewDto); // Service handles conversion
        return ResponseEntity.ok(review); // Or return a ReviewResponseDto if you have one
    }
    @PostMapping("/add_review")
    public Review addReview(@RequestBody ReviewDto reviewDto){
        return reviewsService.createReview(reviewDto);
    }
//    // Get reviews for a product
//    @GetMapping("/product/{productId}")
//    public List<Review> getReviewsByProduct(@PathVariable int productId) {
//        return reviewsService.getReviewsByProduct(productId);
//    }
//
//    // Get reviews by a user
//    @GetMapping("/user/{userId}")
//    public List<Review> getReviewsByUser(@PathVariable int userId) {
//        return reviewsService.getReviewsByUser(userId);
//    }
//
//    // Add a review
//    @PostMapping
//    public Review addReview(@RequestBody Review review) {
//        return reviewsService.addReview(review);
//    }
}
