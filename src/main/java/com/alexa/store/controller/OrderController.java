package com.alexa.store.controller;

import com.alexa.store.dto.OrderDTO;
import com.alexa.store.dto.OrderRequestDto;
import com.alexa.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        OrderDTO createdOrder = orderService.createOrder(orderRequestDto); // Returns OrderDTO
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    // ... other controller methods (make sure they all return OrderDTO)

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable int orderId) {
        OrderDTO order = orderService.getOrderById(orderId); // Returns OrderDTO
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/all-orders")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders(); // Returns List<OrderDTO>
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderDTO>> getOrdersByUser(@PathVariable int userId) {
        List<OrderDTO> orders = orderService.getOrdersByUser(userId); // Returns List<OrderDTO>
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    // ... other controller methods
}