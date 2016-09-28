package com.cbd.backend.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class Inventory {

    private String itemName;
    private String description;
    private int priceInCents;
    private LocalDateTime lastUpdated;
    @Id
    private String id;

    public Inventory(String itemName, String description, int priceInCents, String id, LocalDateTime lastUpdated) {
        this.itemName = itemName;
        this.description = description;
        this.priceInCents = priceInCents;
        this.lastUpdated = lastUpdated;
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriceInCents() {
        return priceInCents;
    }

    public void setPriceInCents(int priceInCents) {
        this.priceInCents = priceInCents;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
