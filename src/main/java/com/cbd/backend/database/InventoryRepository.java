package com.cbd.backend.database;

import com.cbd.backend.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface InventoryRepository extends MongoRepository<Inventory, String> {
    List<Inventory> findAll();
    Inventory findTopByItemOrderByDateTime(String dateTime);
    //BOrderByDateDesc(String item);
}
