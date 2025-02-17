package com.alexa.store.service;

import com.alexa.store.dto.PaymentDTO;
import com.alexa.store.dto.PaymentStatusDTO;
import com.alexa.store.entity.Order;
import com.alexa.store.entity.Payment;
import com.alexa.store.enums.OrderStatus;
import com.alexa.store.enums.PaymentStatus;
import com.alexa.store.exception.OrderNotFoundException;
import com.alexa.store.repository.OrderRepository;
import com.alexa.store.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    public PaymentDTO updatePaymentStatus(PaymentStatusDTO paymentStatusDTO) {

        Order order = orderRepository.findById(paymentStatusDTO.getOrderId())
                .orElseThrow(() -> new OrderNotFoundException("Your order is not found!"));

        Optional<Payment> existingPayment = paymentRepository.findByOrder(order); // Find existing payment

        Payment payment;
        if (existingPayment.isPresent()) {
            payment = existingPayment.get(); // Update existing payment
        } else {
            payment = new Payment(); // Create new payment
            payment.setOrder(order);
        }

        payment.setPaymentMethod(paymentStatusDTO.getPaymentMethod());
        payment.setTransactionId(paymentStatusDTO.getTransactionId());

        if (paymentStatusDTO.getTransactionId() != null) {
            payment.setPaymentStatus(PaymentStatus.COMPLETED);
            order.setOrderStatus(OrderStatus.DELIVERED); // Update Order Status
            orderRepository.save(order); // Save the updated order
        } else {
            payment.setPaymentStatus(PaymentStatus.FAILED);
            order.setOrderStatus(OrderStatus.CANCELLED); // Update Order Status
            orderRepository.save(order);
        }

        payment = paymentRepository.save(payment);

        PaymentDTO paymentDTO = mapToDTO(payment); // Map to DTO

        return paymentDTO;
    }


    private PaymentDTO mapToDTO(Payment payment) {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setPaymentId(payment.getId());
        paymentDTO.setOrder(payment.getOrder());
        paymentDTO.setPaymentMethod(payment.getPaymentMethod());
        paymentDTO.setPaymentStatus(payment.getPaymentStatus());
        paymentDTO.setTransactionId(payment.getTransactionId());
        paymentDTO.setPaymentDate(payment.getPaymentDate());
        paymentDTO.setOrderStatus(payment.getOrder().getOrderStatus()); // Set order status in DTO
        return paymentDTO;
    }


}