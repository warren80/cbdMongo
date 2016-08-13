package com.cbd.backend.service;

import com.cbd.backend.model.Inventory;

public interface InventoryService {
//    String createInventoryItem(String name, String description, String price);
    Inventory getInventoryItem(String name);
}
