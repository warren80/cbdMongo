package com.cbd.backend.model;

import com.cbd.backend.model.Account.dbo.Account;
import com.cbd.backend.model.Account.dbo.Address;
import com.google.gson.Gson;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddressTestFast {

    Gson gson = new Gson();

    @Test
    public void createAddressFast() {
        Address address = gson.fromJson(json, Address.class );
        assertNotNull( address );
        assertTrue(address.getCity().equals("Vancouver"));
        assertTrue(address.getCountry().equals("Canada"));
        assertTrue(address.getPostalCode().equals("v6e 1v6"));
        assertTrue(address.getProvince().equals("BC"));
        assertTrue(address.getStreetNumber().equals("1311 Beach"));
    }

    private final String json = "{ \"city\":\"Vancouver\", \"streetNumber\":\"1311 Beach\", \"postalCode\":\"v6e1v6\", \"province\":\"BC\", \"country\":\"Canada\" }";

}