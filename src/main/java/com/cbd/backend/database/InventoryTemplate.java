package com.cbd.backend.database;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.stereotype.Component;

@Component
public class InventoryTemplate {

    private static final String INVENTORY_COLLECTION = "Inventory";

    MongoTemplate mongoTemplate;
    public InventoryTemplate(@Qualifier("mongoTemplate") MongoTemplate template) {
        this.mongoTemplate = template;
    }

    public void getInventoryList() {
        DBCollection collection = mongoTemplate.getCollection( INVENTORY_COLLECTION );

        List<BasicDBObject> dbObjects = collection.distinct( "itemName" );
        System.out.println( dbObjects );
        return;
    }



}
