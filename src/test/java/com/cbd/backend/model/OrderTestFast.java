package com.cbd.backend.model;

import com.cbd.backend.model.dbo.Order;
import com.google.gson.Gson;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderTestFast {

    Gson gson = new Gson();

    @Test
    public void gsonToOrderFast() {
        Order order = gson.fromJson( orderBody, Order.class );
        assertNotNull(order.getCustomer());
        assertNotNull(order.getOrderItem()[0]);
    }



    private final String orderBody = "{ \"orderItem\":[{ \"itemId\":\"lkasj\", \"count\":2 }, { \"itemId\":\"ldka\", \"count\":3 } ], \"customer\":{ \"name\":\"Warren Voelkl\", \"email\":\"warrenvoelkl@gmail.com\", \"phoneNumber\":\"6043196009\", \"address\":{ \"city\":\"Vancouver\", \"streetNumber\":\"1311 Beach\", \"postalCode\":\"v6e1v6\", \"province\":\"BC\", \"country\":\"Canada\" } } }";
}