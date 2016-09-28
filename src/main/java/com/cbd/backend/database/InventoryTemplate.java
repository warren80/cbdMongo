package com.cbd.backend.database;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import com.cbd.backend.model.Inventory;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.MongoClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class InventoryTemplate {

    public static final String DB_NAME = "journaldev";
    public static final String PERSON_COLLECTION = "Person";
    public static final String MONGO_HOST = "localhost";
    public static final int MONGO_PORT = 27017;

    private static final String INVENTORY_COLLECTION = "Inventory";

    MongoTemplate mongoTemplate;
    public InventoryTemplate(@Qualifier("mongoTemplate") MongoTemplate template) {
        this.mongoTemplate = template;
    }

    public void getInventoryList() {
        DBCollection collection = mongoTemplate.getCollection(INVENTORY_COLLECTION);

        List<BasicDBObject> dbObjects = collection.distinct("itemName");
        System.out.println( dbObjects );
        return;
    }



}
