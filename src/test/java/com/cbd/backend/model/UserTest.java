package com.cbd.backend.model;

import com.cbd.backend.database.UserRepository;
import com.google.gson.Gson;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by warrenvoelkl on 2016-11-21.
 */
public class UserTest {
    Gson gson = new Gson();

    UserRepository userRepository;

    @Test
    public void createUser() {
        long timestamp = System.currentTimeMillis();
        User u = new User();
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new Authority("admin" , true) );
        String username = "wvoelkl";
        String password = "password";
        String firstName = "warren";
        String lastName = "voelkl";
        String email = "warrenvoelkl@gmail.com";
        String account =  "BugsSoftware";


        u.setAuthory( authorities );
        u.setUsername( username );
        u.setPassword( password );
        u.setAccount( account );
        u.setFirstName( firstName );
        u.setLastName( lastName );
        u.setEmail( email );
        u.setPasswordUpdateDate( timestamp );
        u.setLastUpdated( timestamp );
        u.setId( timestamp );

        assertNotNull( u.getAuthority() );
        assertTrue( u.getUsername().equals( username ) );
        assertTrue( u.getPassword().equals( password ) );
        assertTrue( u.getAccount().equals( account ) );
        assertTrue( u.getFirstName().equals( firstName ) );
        assertTrue( u.getLastName().equals( lastName ) );
        assertTrue( u.getEmail().equals( email ) );
        assertEquals( u.getLastUpdated(), timestamp );
    }


}
