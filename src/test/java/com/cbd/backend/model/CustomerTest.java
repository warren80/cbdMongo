package com.cbd.backend.model;

import com.cbd.backend.model.dbo.Customer;
import com.google.gson.Gson;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
public class CustomerTest {

    Gson gson = new Gson();
    @Test
    public void gsonToCustomer() {
        Customer customer = gson.fromJson(jsonBody, Customer.class);
        assertNotNull( customer );
        assertTrue( customer.getPhoneNumber().equals("6043196009") );
    }


    private final String jsonBody = "{ \"email\":\"warrenvoelkl@gmail.com\", \"phoneNumber\":\"6043196009\", \"address\":{ \"city\":\"Vancouver\", \"streetNumber\":\"1311 Beach\", \"postalCode\":\"v6e1v6\", \"province\":\"BC\", \"country\":\"Canada\" }}";
}