# 🍽️ Food Ordering System

A Java + Spring Boot-based in-memory food ordering system that simulates real-world restaurant order processing, auto-assignment of orders using selection strategies, and restaurant management — without the use of any external database.

## 🚀 Project Overview

This application is designed to manage restaurants, customers, and orders in a food delivery ecosystem. The system supports:

- ✅ Onboarding of restaurants with menu, rating, and capacity (`max_no_orders`)
- ✅ Order placement by customers using item list and selection strategy
- ✅ Auto-assignment of orders to restaurants using pluggable selection strategies(rating strategy).
- ✅ Order lifecycle management (ACCEPTED → COMPLETED)
- ✅ In-memory data handling (no database or persistent storage)

## 🔧 Tech Stack

- **Java 17**
- **Spring Boot 3.x**
- **In-memory data structures**
- **Strategy Design Pattern**
- **Thread-safe concurrency (using `ConcurrentHashMap`, `AtomicInteger`)**

## 📌 Key Functional Requirements

1. **Restaurant Management**
   - Onboard a new restaurant with:
     - Name
     - Menu (item + price)
     - Rating (out of 5)
     - Max number of concurrent orders (`max_no_orders`)
   - Update menu (add or update price; deletion not allowed only soft delete)

2. **Customer Management**
   - Add new customers
   - Customers are uniquely identified by name

3. **Order Placement**
   - Customers can place orders with:
     - List of items and quantity
     - Selection strategy:
       - `HIGHEST_RATING`: Auto Assigns to restaurant with best rating.

4. **Order Rules**
 - An order is accepted only if all items are available in one restaurant.
 - A restaurant cannot accept more orders than its max_no_orders limit.
 - Once an order is ACCEPTED: 
 - It cannot be cancelled.
 - It can be marked as COMPLETED by the restaurant.
  (In this project, orders auto-complete after 10 seconds for demo purposes.)
 - A COMPLETED order frees up the restaurant’s capacity to take new orders.

## 🧠 Design Highlights

- **Strategy Pattern**: Easily extendable selection strategies for restaurant assignment
- **Thread-Safe Data Handling**: Uses `ConcurrentHashMap` and `AtomicInteger` to ensure safe concurrent access
- **Modular Structure**: Clean separation of controller, service, model, and repo layers
- **No External Database**: All data is stored and processed in memory for simplicity and performance


## 📌 API Highlights
| Endpoint                          | Method | Description                                               |
|----------------------------------|--------|------------------------------------------------------------|
| `/createRestaurants`             | POST   | Onboard a new restaurant                                   |
| `/restaurants/{id}/updateMenu`   | PUT    | Add or update or soft delete menu items for a restaurant   |
| `/placeOrder`                    | POST   | Place a new order                                          |
| `/placeOrder`                    | GET    | Get all placed orders (or for testing)                     |


## 📄 Test Cases Documentation

You can view the complete test case document with screenshots and descriptions here:

👉 [View Test Cases Document on Google Docs](https://docs.google.com/document/d/1AdnhxhnuteSwk7laAjYZrm89GTlb0ifJgpD-gqeDBPs/edit?usp=sharing)
