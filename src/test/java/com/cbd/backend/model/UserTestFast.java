package com.cbd.backend.model;

import com.cbd.backend.model.dbo.User;
import com.google.gson.Gson;
import org.junit.Test;

import static com.cbd.backend.TestFactories.*;
import static org.junit.Assert.*;

public class UserTestFast {
    Gson gson = new Gson();

    @Test
    public void createUserFast() {
        User u = createFarmAdminUser();

        assertNotNull( u.getAuthority() );
        assertTrue( u.getUsername().equals( username ) );
        assertTrue( u.getPassword().equals( password ) );
        assertTrue( u.getFarm().equals(farm) );
        assertTrue( u.getFirstName().equals( firstName ) );
        assertTrue( u.getLastName().equals( lastName ) );
        assertTrue( u.getEmail().equals( email ) );
    }




}
