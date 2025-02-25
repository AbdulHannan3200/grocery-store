package com.alexa.store.entity;

import com.alexa.store.enums.PaymentMethod;
import com.alexa.store.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", nullable = false , unique = true)
    private int id;

    @OneToOne
    @JoinColumn(name = "order_id", unique = true)
    @JsonBackReference(value = "orderPayment")
    private Order order; // The name should be "order" to be consistent with the @JoinColumn

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method" , nullable = false)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status" , nullable = false)
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    @Column(name = "transaction_id", unique = true, length = 50)
    private String transactionId;

    @Column(name = "payment_date", nullable = false, updatable = false)
    private LocalDateTime paymentDate;

    @PrePersist
    protected void onCreate() {
        this.paymentDate = LocalDateTime.now(); // Automatically sets createdAt when saving
    }
}