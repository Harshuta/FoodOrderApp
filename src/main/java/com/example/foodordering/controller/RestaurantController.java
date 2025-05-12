package com.example.foodordering.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.foodordering.model.MenuUpdateRequest;
import com.example.foodordering.model.Order;
import com.example.foodordering.model.OrderRequest;
import com.example.foodordering.model.Restaurant;
import com.example.foodordering.service.OrderService;
import com.example.foodordering.service.RestaurantService;

@RestController
@RequestMapping("/")
public class RestaurantController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RestaurantService restaurantService;

      @PostMapping("/placeOrder")
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest request) {
        Order order = orderService.placeOrder(request.getCustomerName(), request.getItems());
        Restaurant assignedRestaurant = restaurantService.getRestaurantById(order.getRestaurantId());
        String message = "Order assigned to restaurant: " + assignedRestaurant.getRestName();
        return ResponseEntity.ok(message);
    }

    @PostMapping("/createRestaurants")
    public ResponseEntity<String> onboardRestaurant(@RequestBody Restaurant restaurant) {
        restaurantService.addRestaurant(restaurant);
        return ResponseEntity.ok("Restaurant added successfully");
    }

    @PutMapping("/restaurants/{id}/updateMenu")
    public ResponseEntity<String> updateMenu(@PathVariable int id, @RequestBody MenuUpdateRequest request) {
        restaurantService.updateMenu(id, request);
        return ResponseEntity.ok("Menu updated successfully");
    }
    @GetMapping("/getAllRestaurants")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }

}
