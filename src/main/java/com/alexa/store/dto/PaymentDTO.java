package com.alexa.store.dto;


import com.alexa.store.entity.Order;
import com.alexa.store.enums.OrderStatus;
import com.alexa.store.enums.PaymentMethod;
import com.alexa.store.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private int paymentId; // Changed to int to match entity
    private Order order; // Changed to Order entity
    private OrderStatus orderStatus; // Added order status
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private String transactionId;
    private LocalDateTime paymentDate;
}

