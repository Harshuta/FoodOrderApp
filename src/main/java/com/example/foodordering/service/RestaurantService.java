package com.example.foodordering.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.foodordering.model.MenuItem;
import com.example.foodordering.model.MenuUpdateRequest;
import com.example.foodordering.model.Restaurant;
import com.example.foodordering.repository.RestaurantRepository;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository repository;

    public List<Restaurant> getAllRestaurants() {
        return repository.getAllRestaurants();
    }

    public Restaurant getRestaurantById(int id) {
        return repository.getById(id);
    }

    public void addRestaurant(Restaurant restaurant) {
        if (restaurant == null || restaurant.getRestName() == null || restaurant.getRestName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid restaurant data");
        }

        boolean exists = repository.getAllRestaurants().stream()
                .anyMatch(r -> r.getRestName().equalsIgnoreCase(restaurant.getRestName()));
        if (exists) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Restaurant already exists");
        }

        repository.addRestaurant(restaurant);
    }

    public void updateMenu(int restId, MenuUpdateRequest request) {
        Restaurant restaurant = repository.getById(restId);
        if (restaurant == null) {
            throw new IllegalArgumentException("Restaurant not found");
        }

        Map<String, MenuItem> menuMap = restaurant.getMenu().stream()
            .collect(Collectors.toMap(item -> item.getName().toLowerCase(), item -> item));

        for (MenuItem update : request.getUpdates()) {
            if (update.getName() == null || update.getName().isBlank()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Menu item name cannot be blank");
            }

            String key = update.getName().toLowerCase();
            if (menuMap.containsKey(key)) {
                MenuItem existingItem = menuMap.get(key);
                existingItem.setPrice(update.getPrice());
                existingItem.setActive(update.isActive());
            } else {
                restaurant.getMenu().add(new MenuItem(update.getName(), update.getPrice(), update.isActive()));
            }
        }
    }

    public List<Restaurant> findEligibleRestaurants(Map<String, Integer> requestedItems) {
        return repository.getAllRestaurants().stream()
            .filter(restaurant -> restaurant.getCurrentOrders() < restaurant.getMaxNoOfOrders())
            .filter(restaurant -> requestedItems.keySet().stream().allMatch(
                item -> restaurant.getMenu().stream().anyMatch(
                    menuItem -> menuItem.getName().equalsIgnoreCase(item) && menuItem.isActive()
                )
            ))
            .collect(Collectors.toList());
    }
}
