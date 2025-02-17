package com.alexa.store.service;

import com.alexa.store.dto.OrderDTO; // Correct import
import com.alexa.store.dto.OrderRequestDto;
import com.alexa.store.dto.ProductDTO;
import com.alexa.store.dto.UserDto;
import com.alexa.store.entity.Order;
import com.alexa.store.entity.Product;
import com.alexa.store.entity.User;
import com.alexa.store.enums.OrderStatus;
import com.alexa.store.exception.InsufficientStockException;
import com.alexa.store.exception.OrderNotFoundException;
import com.alexa.store.exception.ProductNotFoundException;
import com.alexa.store.exception.UserNotFoundException;
import com.alexa.store.repository.OrderRepository;
import com.alexa.store.repository.ProductRepository;
import com.alexa.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors; // Import for stream

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    public OrderDTO createOrder(OrderRequestDto orderRequestDto) {
        User user = userRepository.findById(orderRequestDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Product product = productRepository.findById(orderRequestDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        if (product.getStockQuantity() < orderRequestDto.getQuantity()) {
            throw new InsufficientStockException("Insufficient stock for product " + product.getName());
        }

        Order order = new Order();
        order.setUser(user);
        order.setProduct(product);
        order.setQuantity(orderRequestDto.getQuantity());
        order.setPrice(product.getPrice().multiply(BigDecimal.valueOf(orderRequestDto.getQuantity())));
        order.setOrderStatus(OrderStatus.PENDING);

        product.setStockQuantity(product.getStockQuantity() - orderRequestDto.getQuantity());
        productRepository.save(product); // Save the updated product

        order = orderRepository.save(order); // Save the order

        return convertToOrderDto(order); // Convert the saved order to DTO
    }


    private UserDto convertToUserDto(User user) {
//        logger.info("Converting User to DTO: User ID = {}, Email = {}", user.getId(), user.getEmail()); // Log email
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastname(user.getLastName());
        userDto.setEmail(user.getEmail()); // Make sure to set the email if you want it in the DTO
        return userDto;
    }

    private ProductDTO convertToProductDto(Product product) {
//        logger.info("Converting Product to DTO: Product ID = {}, Description = {}, Price = {}",
//                product.getId(), product.getDescription(), product.getPrice()); // Log description and price
        ProductDTO productDto = new ProductDTO();
        productDto.setProductId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription()); // Set description
        productDto.setPrice(product.getPrice()); // Set price
        return productDto;
    }

    private OrderDTO convertToOrderDto(Order order) {
        OrderDTO orderDto = new OrderDTO();
        orderDto.setOrderId(order.getOrderId());
        orderDto.setUser(convertToUserDto(order.getUser())); // Convert User to UserDto
        orderDto.setProduct(convertToProductDto(order.getProduct())); // Convert Product to ProductDTO
        orderDto.setQuantity(order.getQuantity());
        orderDto.setPrice(order.getPrice());
        orderDto.setOrderDate(order.getOrderDate());
        orderDto.setOrderStatus(order.getOrderStatus());
        return orderDto;
    }


    public Order updateOrderStatus(int orderId, OrderStatus newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        order.setOrderStatus(newStatus);
        return orderRepository.save(order);
    }

    public OrderDTO getOrderById(int orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        return convertToOrderDto(order);
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::convertToOrderDto) // Convert each Order to OrderDTO
                .collect(Collectors.toList()); // Collect into a List<OrderDTO>
    }

    public List<OrderDTO> getOrdersByUser(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return orderRepository.findByUser(user).stream()
                .map(this::convertToOrderDto)
                .collect(Collectors.toList());
    }

    // ... other methods as needed
}