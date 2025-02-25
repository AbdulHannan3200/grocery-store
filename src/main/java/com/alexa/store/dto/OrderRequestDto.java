package com.alexa.store.dto;
// DTO for Order Requests (what the client sends)
public class OrderRequestDto {
    private int userId;
    private int productId;
    private int quantity;

    // Getters and setters
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

}