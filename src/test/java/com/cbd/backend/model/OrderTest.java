package com.cbd.backend.model;

import com.cbd.backend.model.dbo.Order;
import com.google.gson.Gson;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderTest {

    Gson gson = new Gson();

    @Test
    public void gsonToOrder() {
        Order order = gson.fromJson( orderBody, Order.class );
        assertNotNull(order.getCustomer());
        assertNotNull(order.getOrderItem()[0]);
    }



    private final String orderBody = "{ \"orderItem\":[{ \"itemId\":\"lkasj\", \"count\":2 }, { \"itemId\":\"ldka\", \"count\":3 } ], \"customer\":{ \"name\":\"Warren Voelkl\", \"email\":\"warrenvoelkl@gmail.com\", \"phoneNumber\":\"6043196009\", \"address\":{ \"city\":\"Vancouver\", \"streetNumber\":\"1311 Beach\", \"postalCode\":\"v6e1v6\", \"province\":\"BC\", \"country\":\"Canada\" } } }";

//    private final String a = "{\n" +
//            "    \"customerAddress\":{\n" +
//            "        \"city\":\"seattle\"\n" +
//            "    },\n" +
//            "    \"orderItems\":[\n" +
//            "        {\n" +
//            "            \"itemId\":\"lkasj\",\n" +
//            "            \"count\":2\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"itemId\":\"ldka\",\n" +
//            "            \"count\":3\n" +
//            "        }\n" +
//            "    ]\n" +
//            "}";
}