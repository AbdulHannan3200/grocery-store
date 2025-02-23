package com.alexa.store.entity;

import com.alexa.store.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders") // Corrected table name to plural "orders" for consistency
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference(value = "userOrders") // Unique name
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @JsonBackReference(value = "productOrders") // Unique name
    private Product product;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING) // Use Enum for OrderStatus
    @Column(name = "status")
    private OrderStatus orderStatus;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order") // mappedBy = "order" from Payment Entity
    @JsonManagedReference(value = "orderPayment")
    private Payment payment;

    @PrePersist
    protected void onCreate() {
        this.orderDate = LocalDateTime.now();
    }
}

