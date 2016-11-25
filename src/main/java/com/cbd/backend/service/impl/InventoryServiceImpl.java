package com.cbd.backend.service.impl;

import com.cbd.backend.database.InventoryRepository;
import com.cbd.backend.database.InventoryTemplate;
import com.cbd.backend.model.dbo.Inventory;
import com.cbd.backend.service.InventoryService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    Gson gson = new Gson();
    static Logger log = Logger.getLogger(InventoryServiceImpl.class.getName());

    @Override
    public void addInventory() {
        long timeStamp = System.currentTimeMillis();
        String jsonString = null;
        try {
            jsonString = FileUtils.readFileToString( new File( "sampleData.txt" ), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Type listType = new TypeToken<List<Inventory>>() {}.getType();

        //inventoryRepository.deleteAll();

        List<Inventory> inventories = gson.fromJson(jsonString, listType );
        for(Inventory inventory : inventories) {
            inventory.setLastUpdated(timeStamp);
            inventoryRepository.save(inventory);
            System.out.println(inventory);
        }
    }

    @Autowired
    InventoryTemplate template;

    public  String getInventory() {
        List<Inventory> unfilteredInventory = inventoryRepository.findAll();
        List<Inventory> fileredList = filterList(unfilteredInventory);
        return gson.toJson( fileredList );
    }

    @Override
    public String getInventoryItem(String name){
        Inventory item = inventoryRepository.findTopByItemNameOrderByLastUpdatedDesc( name );
        return gson.toJson( item );
    }

    private List<Inventory> filterList( List<Inventory> unfilteredInventory ) {

        HashSet<String> values = new HashSet<String>();
        List<Inventory> filteredList = new ArrayList<Inventory>();
        for (Inventory inv: unfilteredInventory) {
            values.add(inv.getItemName() );
        }
        for( String v: values) {
                filteredList.add(unfilteredInventory.stream().filter(n -> n.getItemName().equals(v)).max(Comparator.comparing(u -> u.getLastUpdated())).get());
        }

        return filteredList;
    }
}
