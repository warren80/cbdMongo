package com.cbd.backend.model;

import org.springframework.data.annotation.Id;

public class OrderItem {
    @Id
    private String id;
    private String itemId;
    private String count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
