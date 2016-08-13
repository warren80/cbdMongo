package com.cbd.backend.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class OrderItemsTest {
    Gson gson = new Gson();
    @Test
    public void gsonToOrderItems() {
        //Type listType = new TypeToken<List<OrderItem>>(){}.getType();
        //List<OrderItem> orderItems = gson.fromJson( jsonBody, listType );
        OrderItem[] orderItems = gson.fromJson( jsonBody, OrderItem[].class );

        assertNotNull( orderItems );
    }
    final String jsonBody = "[{ \"itemId\":\"lkasj\", \"count\":2 }, { \"itemId\":\"ldka\", \"count\":3 } ]";
    //private final String jsonBody = "[{ \"itemId\":\"lkasj\", \"count\":2 }, { \"itemId\":\"ldka\", \"count\":3 } ]";
}