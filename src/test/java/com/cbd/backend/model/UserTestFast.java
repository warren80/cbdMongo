package com.cbd.backend.model;

import com.cbd.backend.database.UserRepository;
import com.cbd.backend.model.dbo.Authority;
import com.cbd.backend.model.dbo.User;
import com.google.gson.Gson;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.cbd.backend.model.AuthorityPermission.ROLE_ACCOUNT_ADMIN;
import static com.cbd.backend.model.AuthorityPermission.ROLE_SITE_USER;
import static org.junit.Assert.*;

public class UserTestFast {
    Gson gson = new Gson();

    UserRepository userRepository;

    private static String username = "wvoelkl";
    private static String password = "password";
    private static String firstName = "warren";
    private static String lastName = "voelkl";
    private static String email = "warrenvoelkl@gmail.com";
    private static String account =  "BugsSoftware";

    @Test
    public void createUserFast() {
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
        authorities.add(new Authority( ROLE_ACCOUNT_ADMIN , true) );


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
    public static NewUser getAccountUser( final String account ) {
        long timestamp = System.currentTimeMillis();
        NewUser u = new NewUser();
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new Authority( ROLE_SITE_USER, true) );


        u.setAuthority( authorities );
        u.setUsername( new String( "newUser" ) );
        u.setPassword( new String( "vA!Id001" ) );
        u.setPasswordCheck( "vA!Id001" );
        u.setAccount( account );
        u.setFirstName( firstName );
        u.setLastName( lastName );
        u.setEmail( email );
        u.setPasswordUpdateDate( timestamp );
        u.setLastUpdated( timestamp );
        u.setSecurityId( 1L );

        return u;
    }

    public static NewUser getNewUser() {
        long timestamp = System.currentTimeMillis();
        NewUser u = new NewUser();
        List<Authority> authorities = new ArrayList<>();
        authorities.add( new Authority( ROLE_ACCOUNT_ADMIN, true ) );
        authorities.add( new Authority( ROLE_SITE_USER, true ) );


        u.setAuthority( authorities );
        u.setUsername( new String( "newUser" ) );
        u.setPassword( new String( "vA!Id001" ) );
        u.setPasswordCheck( "vA!Id001" );
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
