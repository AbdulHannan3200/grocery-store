package com.alexa.store.entity;

import aj.org.objectweb.asm.commons.Remapper;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;

    private String name;

    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "stock_quantity")
    private int stockQuantity;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "productReviews")
    private List<Review> reviews;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "productOrders") // Add @JsonManagedReference with unique name
    private List<Order> orders;

}
