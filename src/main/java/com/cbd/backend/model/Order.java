package com.cbd.backend.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Order {
    private String name;
    private OrderItem[] orderItems;
    private LocalDateTime dateTime;
    private Customer customer;
    private String id;

    Order(String name, OrderItem[] orderItems, Customer customer, String id ) {
        this.dateTime = LocalDateTime.now();
        this.orderItems = orderItems;
        this.id = id;
        this.customer = customer;
        dateTime = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public OrderItem[] getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(OrderItem[] orderItems) {
        this.orderItems = orderItems;
    }
}
