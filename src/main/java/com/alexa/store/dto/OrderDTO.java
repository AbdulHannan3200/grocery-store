package com.alexa.store.dto;

import com.alexa.store.enums.OrderStatus; // Import OrderStatus
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

// DTO for Order Responses (what the server sends back)
public class OrderDTO {  // Renamed to OrderDTO for response
    private Long orderId;
    private UserDto user; // Use UserDto
    private ProductDTO product; // Use ProductDTO
    private int quantity;
    private BigDecimal price;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;

}


