package com.example.foodordering.model;

public class OrderItem {
    private int orderItemId;
    private String itemName;
    private int quantity;
    private int priceAtOrderTime;

    public OrderItem(int orderItemId, String itemName, int quantity, int priceAtOrderTime) {
        this.orderItemId = orderItemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.priceAtOrderTime = priceAtOrderTime;
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPriceAtOrderTime() {
        return priceAtOrderTime;
    }
}
