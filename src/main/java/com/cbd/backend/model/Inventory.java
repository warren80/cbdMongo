package com.cbd.backend.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class Inventory {

    private String item;
    private String description;
    private int price;
    private LocalDateTime dateTime;

    @Id
    private final String id;

    public Inventory(String item, String description, int price, String id) {
        this.item = item;
        this.description = description;
        this.price = price;
        this.dateTime = LocalDateTime.now();
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
