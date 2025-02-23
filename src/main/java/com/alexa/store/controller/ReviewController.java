package com.alexa.store.controller;

import com.alexa.store.dto.ReviewRequestDto;
import com.alexa.store.dto.ReviewResponseDTO;
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

    @PostMapping("/add-review")
    public Review addReview(@RequestBody ReviewRequestDto reviewRequestDto){
        return reviewsService.createReview(reviewRequestDto);
    }
    @GetMapping("/reviews-list")
    public ResponseEntity<List<ReviewResponseDTO>> getAll(){
        List<ReviewResponseDTO> reviews = reviewsService.getAll();
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ReviewResponseDTO>> getByProduct(@PathVariable int productId) {
        List<ReviewResponseDTO> reviews = reviewsService.getReviewsByProduct(productId);
        return ResponseEntity.ok(reviews);
    }

}
