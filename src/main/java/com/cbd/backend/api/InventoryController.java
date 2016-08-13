package com.cbd.backend.api;

import com.cbd.backend.model.Inventory;
import com.cbd.backend.service.impl.InventoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {

    @Autowired
    InventoryServiceImpl inventoryServiceImpl;

//    @RequestMapping("/addInventory")
//    public String addInventory(@RequestParam(value="name") String name,
//                               @RequestParam(value="description") String description,
//                               @RequestParam(value="price") String price) {
//        String result = inventoryServiceImpl.createInventoryItem( name, description, price );
//        return  result;
//    }

    @RequestMapping("/getInventory")
    public Inventory getInventory (@RequestParam(value="name") String name) {
        return inventoryServiceImpl.getInventoryItem( name );
    }
}
