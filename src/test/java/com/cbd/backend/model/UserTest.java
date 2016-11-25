package com.cbd.backend.model;

import com.cbd.backend.database.UserRepository;
import com.cbd.backend.model.dbo.Authority;
import com.cbd.backend.model.dbo.User;
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

    private static String username = "wvoelkl";
    private static String password = "password";
    private static String firstName = "warren";
    private static String lastName = "voelkl";
    private static String email = "warrenvoelkl@gmail.com";
    private static String account =  "BugsSoftware";

    @Test
    public void createUser() {
        User u = getStockUser();

        assertNotNull( u.getAuthority() );
        assertTrue( u.getUsername().equals( username ) );
        assertTrue( u.getPassword().equals( password ) );
        assertTrue( u.getAccount().equals( account ) );
        assertTrue( u.getFirstName().equals( firstName ) );
        assertTrue( u.getLastName().equals( lastName ) );
        assertTrue( u.getEmail().equals( email ) );
    }

    public static User getStockUser() {
        long timestamp = System.currentTimeMillis();
        User u = new User();
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new Authority("admin" , true) );


        u.setAuthority( authorities );
        u.setUsername( username );
        u.setPassword( password );
        u.setAccount( account );
        u.setFirstName( firstName );
        u.setLastName( lastName );
        u.setEmail( email );
        u.setPasswordUpdateDate( timestamp );
        u.setLastUpdated( timestamp );
        u.setSecurityId( 1L );

        return u;
    }


}
