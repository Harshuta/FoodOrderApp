package com.example.foodordering.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Repository;

import com.example.foodordering.model.Order;

@Repository
public class OrderRepository {

    private final List<Order> orders = new ArrayList<>();
    private final AtomicInteger orderIdGenerator = new AtomicInteger(1);
    private final AtomicInteger orderItemIdGenerator = new AtomicInteger(1);

    public int generateOrderId() {
        return orderIdGenerator.getAndIncrement();
    }

    public int generateOrderItemId() {
        return orderItemIdGenerator.getAndIncrement();
    }

    public void saveOrder(Order order) {
        orders.add(order);
    }

    public List<Order> findAllOrders() {
        return orders;
    }
}
