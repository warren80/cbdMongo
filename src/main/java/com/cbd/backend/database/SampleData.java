package com.cbd.backend.database;

import com.cbd.backend.model.Inventory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

@Component
public class SampleData {

    @Autowired()
    InventoryRepository inventoryRepository;

    Gson gson = new Gson();

    public void loadData() {

    }


}
