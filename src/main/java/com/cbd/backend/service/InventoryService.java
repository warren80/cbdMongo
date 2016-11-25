package com.cbd.backend.service;


public interface InventoryService {
//    String createInventoryItem(String name, String description, String price);
    String getInventoryItem(String name);
    void addInventory();
}
