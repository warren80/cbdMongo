package com.cbd.backend.database;

import com.cbd.backend.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository()
public interface InventoryRepository extends MongoRepository<Inventory, String> {
    List<Inventory> findAll();
    Inventory findTopByItemNameOrderByLastUpdatedDesc(String itemName);
    Inventory[] findByItemNameOrderByLastUpdatedAsc(String itemName);
    Inventory[] findByItemName( String itemName );
}
