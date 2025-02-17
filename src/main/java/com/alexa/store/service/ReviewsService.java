package com.alexa.store.service;

import com.alexa.store.dto.ReviewDto;
import com.alexa.store.entity.Product;
import com.alexa.store.entity.Review;
import com.alexa.store.entity.User;
import com.alexa.store.exception.ProductNotFoundException;
import com.alexa.store.exception.UserNotFoundException;
import com.alexa.store.repository.ProductRepository;
import com.alexa.store.repository.ReviewRepository;
import com.alexa.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewsService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;


    public List<Review> getAll(){
        return reviewRepository.findAll();
    }
//    private final UserRepository userRepository;
//
//    @Autowired
//    public ReviewsService(ReviewRepository reviewRepository, UserRepository userRepository) {
//        this.reviewRepository = reviewRepository;
//        this.userRepository = userRepository;
//    }
//
//    public List<Review> getAllReviewsByUserId(int userId) {
//
//        return reviewRepository.findByUserId(userId);
//    }
//
//    public List<Review> getReviewsByProduct(int productId) {
//        Optional<Product> existingUser= reviewRepository.findById(productId);
//
//        if (existingUser.isEmpty()) {
//            throw new UserNotFoundException("This user has not given any reviews about any item!");
//        }
//
//        // Use the found User object
//        return reviewRepository.findByUserId(existingUser.get());
//    }
//
//    public List<Review> getReviewsByUser(int userId) {
//        Optional<User> existingUser = userRepository.findById(userId);
//
//        if (existingUser.isEmpty()) {
//            throw new UserNotFoundException("This user has not given any reviews about any item!");
//        }
//
//        // Use the found User object
//        return reviewRepository.findByUserId(existingUser.get());
//    }



    public Review createReview(ReviewDto reviewDto) {
        User user = userRepository.findById(reviewDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Product product = productRepository.findById(reviewDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setRating(reviewDto.getRating());
        review.setComment(reviewDto.getComment());

        return reviewRepository.save(review);
    }
}
