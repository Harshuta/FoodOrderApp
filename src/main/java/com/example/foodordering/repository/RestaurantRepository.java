package com.example.foodordering.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.foodordering.model.MenuItem;
import com.example.foodordering.model.Restaurant;

import jakarta.annotation.PostConstruct;

@Repository
public class RestaurantRepository {
    private final List<Restaurant> restaurants = new ArrayList<>();

    @PostConstruct
    public void init() {
        List<MenuItem> menu1 = new ArrayList<>();
        menu1.add(new MenuItem("Pizza", 200, true));
        menu1.add(new MenuItem("Burger", 100, true));
        menu1.add(new MenuItem("Pasta", 150, true));

        List<MenuItem> menu2 = new ArrayList<>();
        menu2.add(new MenuItem("Pizza", 180, true));
        menu2.add(new MenuItem("Burger", 90, true));
        menu2.add(new MenuItem("Fries", 60, true));

        List<MenuItem> menu3 = new ArrayList<>();
    menu3.add(new MenuItem("Sushi", 500, true)); 
    menu3.add(new MenuItem("Miso Soup", 120, true));  
    menu3.add(new MenuItem("Tempura", 350, false));  

    List<MenuItem> menu4 = new ArrayList<>();
    menu4.add(new MenuItem("Salad", 120, true));  
    menu4.add(new MenuItem("Soup", 100, true));   
    menu4.add(new MenuItem("Smoothie", 130, true)); 

    List<MenuItem> menu5 = new ArrayList<>();
    menu5.add(new MenuItem("Salad", 250, true));  
    menu5.add(new MenuItem("Pizza", 80, true));  
    menu5.add(new MenuItem("Burger", 50, true));  

    List<MenuItem> menu6 = new ArrayList<>();
    menu6.add(new MenuItem("Cheeseburger", 120, false));  
    menu6.add(new MenuItem("Veggie Wrap", 110, true));    
    menu6.add(new MenuItem("Coke", 40, true));             

    restaurants.add(new Restaurant(1, "Foodies Hub", 4.5, 5, menu1));
    restaurants.add(new Restaurant(2, "Yummy Treats", 4.7, 5, menu2));
    restaurants.add(new Restaurant(3, "Sushi World", 4.9, 5, menu3));
    restaurants.add(new Restaurant(4, "Green Garden", 4.3, 5, menu4));
    restaurants.add(new Restaurant(5, "Seafood Paradise", 4.8, 5, menu5));
    restaurants.add(new Restaurant(6, "Burger Bistro", 4.2, 5, menu6));
    }


    public List<Restaurant> getAllRestaurants() {
        return restaurants;
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

    public Restaurant getById(int id) {
        return restaurants.stream().filter(r -> r.getRestId() == id).findFirst().orElse(null);
    }
}
