package com.example.foodordering.service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.example.foodordering.model.Customer;

@Service
public class CustomerService {
    private ConcurrentHashMap<String, Customer> customerMap = new ConcurrentHashMap<>();
    private AtomicInteger customerIdGenerator = new AtomicInteger(1);

    public Customer getOrCreateCustomer(String customerName) {
        if (customerName == null || customerName.isBlank()) {
            throw new IllegalArgumentException("Customer name cannot be null or empty");
        }
        return customerMap.computeIfAbsent(customerName, name ->
            new Customer(customerIdGenerator.getAndIncrement(), name)
        );
    }
}
