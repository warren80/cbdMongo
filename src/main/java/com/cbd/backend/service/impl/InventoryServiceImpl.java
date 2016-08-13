package com.cbd.backend.service.impl;

import com.cbd.backend.database.InventoryRepository;
import com.cbd.backend.model.Inventory;
import com.cbd.backend.service.InventoryService;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    InventoryRepository repository;

    Gson gson = new Gson();
    static Logger log = Logger.getLogger(InventoryServiceImpl.class.getName());

//    @Override
//    public String createInventoryItem(String item, String description, String price) {
//        BigDecimal bigDecimalPrice = new BigDecimal( price ).scaleByPowerOfTen(2);
//        int priceInCents = bigDecimalPrice.intValue();
//        LocalDateTime dateTime = LocalDateTime.now();
//
//        Inventory i = new Inventory( item, description, priceInCents, dateTime, null);
//        log.debug( "Saving new Inventory item " + i);
//        Inventory savedItem = null;
//        try {
//            savedItem = repository.save(i);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//        log.trace( "Item saved + i" );
//        return gson.toJson( savedItem );
//    }

    @Override
    public Inventory getInventoryItem(String name) {
        return repository.findTopByItemOrderByDateTime( name );
    }

}
