package com.alexa.store.repository;

import com.alexa.store.entity.Product;
import com.alexa.store.entity.Review;
import com.alexa.store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
//    Optional<User> findUser();
//    List<Review> findByProductId(Product productId);
//    List<Review> findByUserId(User userId);
}
