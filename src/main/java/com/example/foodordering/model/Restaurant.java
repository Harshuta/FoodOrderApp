package com.example.foodordering.model;

import java.util.List;

public class Restaurant {
    private int restId;
    private String restName;
    private double rating;
    private int maxNoOfOrders;
    private int currentOrders;
    private List<MenuItem> menu;

    public Restaurant(int restId, String restName, double rating, int maxNoOfOrders, List<MenuItem> menu) {
        this.restId = restId;
        this.restName = restName;
        this.rating = rating;
        this.maxNoOfOrders = maxNoOfOrders;
        this.currentOrders = 0;
        this.menu = menu;
    }

    public int getRestId() {
        return restId;
    }

    public String getRestName() {
        return restName;
    }

    public double getRating() {
        return rating;
    }

    public int getMaxNoOfOrders() {
        return maxNoOfOrders;
    }

    public int getCurrentOrders() {
        return currentOrders;
    }

    public void setCurrentOrders(int currentOrders) {
        this.currentOrders = currentOrders;
    }

    public List<MenuItem> getMenu() {
        return menu;
    }
}

