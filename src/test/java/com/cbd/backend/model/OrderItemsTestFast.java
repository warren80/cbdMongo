package com.cbd.backend.model;

import com.cbd.backend.model.dbo.OrderItem;
import com.google.gson.Gson;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderItemsTestFast {
    Gson gson = new Gson();
    @Test
    public void gsonToOrderItemsFast() {
        //Type listType = new TypeToken<List<OrderItem>>(){}.getType();
        //List<OrderItem> orderItems = gson.fromJson( jsonBody, listType );
        OrderItem[] orderItems = gson.fromJson( jsonBody, OrderItem[].class );

        assertNotNull( orderItems );
    }
    final String jsonBody = "[{ \"itemId\":\"lkasj\", \"count\":2 }, { \"itemId\":\"ldka\", \"count\":3 } ]";
    //private final String jsonBody = "[{ \"itemId\":\"lkasj\", \"count\":2 }, { \"itemId\":\"ldka\", \"count\":3 } ]";
}