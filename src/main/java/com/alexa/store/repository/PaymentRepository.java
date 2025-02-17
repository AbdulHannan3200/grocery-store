package com.alexa.store.repository;

import com.alexa.store.entity.Order;
import com.alexa.store.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Optional<Payment> findByOrder(Order order);
}
