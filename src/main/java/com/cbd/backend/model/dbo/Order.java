package com.cbd.backend.model.dbo;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class Order {
    private OrderItem[] orderItem;
    private LocalDateTime dateTime;
    private Customer customer;
    @Id
    private String id;
    private String customerId;

    Order(OrderItem[] orderItem, Customer customer, String id ) {
        this.orderItem = orderItem;
        this.id = id;
        this.customer = customer;
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

    public OrderItem[] getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem[] orderItem) {
        this.orderItem = orderItem;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
