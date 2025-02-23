package com.alexa.store.service;

import com.alexa.store.dto.OrderDTO;
import com.alexa.store.dto.ReviewRequestDto;
import com.alexa.store.dto.ReviewResponseDTO;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewsService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;


    public List<ReviewResponseDTO> getAll(){
        return reviewRepository.findAll()
                .stream()
                .map(this::convertToReviewResponseDto)
                .collect(Collectors.toList());
    }

    public Review createReview(ReviewRequestDto reviewRequestDto) {
        User user = userRepository.findById(reviewRequestDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Product product = productRepository.findById(reviewRequestDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setRating(reviewRequestDto.getRating());
        review.setComment(reviewRequestDto.getComment());

        return reviewRepository.save(review);
    }


    public List<ReviewResponseDTO> getReviewsByProduct(int productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found"));

        return reviewRepository.findByProduct(product).stream()
                .map(this::convertToReviewResponseDto)
                .collect(Collectors.toList());
    }

    public ReviewResponseDTO convertToReviewResponseDto(Review review) {
        ReviewResponseDTO reviewResponseDTO = new ReviewResponseDTO();

        String userName = (review.getUser() != null) ? review.getUser().getName() : "Anonymous";
        String productName = (review.getProduct() != null) ? review.getProduct().getName() : "Unknown Product";

        reviewResponseDTO.setUserName(userName);
        reviewResponseDTO.setProductName(productName);
        reviewResponseDTO.setComment(review.getComment());
        reviewResponseDTO.setRating(review.getRating());

        return reviewResponseDTO;
    }




}
