package com.cbd.backend.restApi.secured;

import com.cbd.backend.service.impl.InventoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryRestController {

    @Autowired
    InventoryServiceImpl inventoryServiceImpl;

    @RequestMapping(value = "${api.inventory}")
    public String addInventory() {
        inventoryServiceImpl.addInventory();
        return "Data Loaded";
    }

    @RequestMapping("/getInventoryItem")
    public String getInventory (@RequestParam(value="name") String name) {
        return inventoryServiceImpl.getInventoryItem( name );
    }

    @RequestMapping("/getInventory")
    public String getInventory () {
        return inventoryServiceImpl.getInventory();
    }
}
