package com.example.foodordering.model;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private int orderId;
    private int customerId;
    private int restaurantId;
    private int totalCost;
    private String status;
    private LocalDateTime createdAt;
    private List<OrderItem> orderItems;

    public Order(int orderId, int customerId, int restaurantId, int totalCost, String status, LocalDateTime createdAt, List<OrderItem> orderItems) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.totalCost = totalCost;
        this.status = status;
        this.createdAt = createdAt;
        this.orderItems = orderItems;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
}

