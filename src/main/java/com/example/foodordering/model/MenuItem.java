package com.example.foodordering.model;

public class MenuItem {
    private String name;
    private int price;
    private boolean isActive;

    public MenuItem(String name, int price, boolean isActive) {
        this.name = name;
        this.price = price;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

     public boolean isActive() {
        return isActive;
    }


    public void setPrice(int price) {
        this.price = price;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}

