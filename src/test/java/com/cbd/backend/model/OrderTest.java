package com.cbd.backend.model;

import com.google.gson.Gson;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderTest {

    Gson gson = new Gson();

    @Test
    public void gsonToOrder() {
        Order order = gson.fromJson( a, Order.class );
        assertNotNull(order);
        assertNotNull(order.getOrderItems());
    }
    private final String a = "{\n"+
            "   \"address\":{\n"+
            "      \"city\":\"seattle\"\n"+
            "   },\n"+
            "   \"orderItem\":[\n"+
            "      {\n"+
            "         \"itemId\":\"lkasj\",\n"+
            "         \"count\":2\n"+
            "      },\n"+
            "      {\n"+
            "         \"itemId\":\"ldka\",\n"+
            "         \"count\":3\n"+
            "      }\n"+
            "   ]\n"+
            "}";
    private final String gsonBody = "{ \"name\":\"Warren Voelkl\", \"customer\":{ \"email\":\"warrenvoelkl@gmail.com\", \"phoneNumber\":\"6043196009\", \"address\":{ \"city\":\"Vancouver\", \"streetNumber\":\"1311 Beach\", \"postalCode\":\"v6e1v6\", \"province\":\"BC\", \"country\":\"Canada\" }, orderItem:[{ \"itemId\":\"lkasj\", \"count\":2 }, { \"itemId\":\"ldka\", \"count\":3 } ] }}";
}