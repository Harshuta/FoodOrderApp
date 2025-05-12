package com.example.foodordering.service;

import com.example.foodordering.model.*;
import com.example.foodordering.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderService {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderRepository orderRepository;

    public Order placeOrder(String customerName, Map<String, Integer> items) {

        if (customerName == null || customerName.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer name cannot be empty");
        }
        if (items == null || items.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order items cannot be empty");
        }

        Customer customer = customerService.getOrCreateCustomer(customerName);

        List<Restaurant> eligibleRestaurants = new ArrayList<>();
        for (Restaurant restaurant : restaurantService.getAllRestaurants()) {
            if (restaurant.getCurrentOrders() >= restaurant.getMaxNoOfOrders()) {
                continue;
            }

            boolean allItemsAvailable = items.keySet().stream()
                    .allMatch(itemName -> restaurant.getMenu().stream()
                            .anyMatch(menuItem -> menuItem.getName().equalsIgnoreCase(itemName) && menuItem.isActive()));

            if (allItemsAvailable) {
                eligibleRestaurants.add(restaurant);
            }
        }

        if (eligibleRestaurants.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No eligible restaurant found for the requested items.");
        }

        eligibleRestaurants.sort((r1, r2) -> Double.compare(r2.getRating(), r1.getRating()));
        Restaurant selectedRestaurant = eligibleRestaurants.get(0);

        List<OrderItem> orderItems = new ArrayList<>();
        int totalCost = 0;

        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            String itemName = entry.getKey();
            int quantity = entry.getValue();

            MenuItem menuItem = selectedRestaurant.getMenu().stream()
                    .filter(item -> item.getName().equalsIgnoreCase(itemName))
                    .findFirst()
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item not found: " + itemName));

            int price = menuItem.getPrice();
            orderItems.add(new OrderItem(orderRepository.generateOrderItemId(), itemName, quantity, price));
            totalCost += price * quantity;
        }

        Order order = new Order(
                orderRepository.generateOrderId(),
                customer.getCustomerId(),
                selectedRestaurant.getRestId(),
                totalCost,
                "ACCEPTED",
                LocalDateTime.now(),
                orderItems
        );

        orderRepository.saveOrder(order);
        selectedRestaurant.setCurrentOrders(selectedRestaurant.getCurrentOrders() + 1);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                order.setStatus("COMPLETED");
                selectedRestaurant.setCurrentOrders(selectedRestaurant.getCurrentOrders() - 1);
            }
        }, 50000);

        return order;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAllOrders();
    }
}


