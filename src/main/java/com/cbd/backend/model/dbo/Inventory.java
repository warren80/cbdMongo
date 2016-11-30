package com.cbd.backend.model.dbo;

import com.cbd.backend.model.Model;
import org.springframework.data.annotation.Id;

public class Inventory extends Model {

    private String itemName;
    private String description;
    @Id
    private String id;

    public Inventory(String itemName, String description, String id, long lastUpdated) {
        this.itemName = itemName;
        this.description = description;
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

    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated( final long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
