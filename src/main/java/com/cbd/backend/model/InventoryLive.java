package com.cbd.backend.model;

import java.time.LocalDateTime;

/**
 * Created by warrenvoelkl on 2016-11-24.
 */
public class InventoryLive extends Inventory {

    public InventoryLive( String itemName, String description, String id, long lastUpdated, String other) {
        super(itemName, description, id, lastUpdated);

    }
}
