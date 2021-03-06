package com.cbd.backend.common;

import com.cbd.backend.model.dbo.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

import static com.cbd.backend.TestFactories.*;
import static org.junit.Assert.*;

public class HelpersTest {
//    @Test
//    public void objectToJsonPasswordStrippedOutTestFast() throws Exception {
//        User u = createOrganizationAdminUser();
//        String result = Helpers.objectToJson( u );
//        assertTrue( u.getPassword() != null);
//        ObjectMapper mapper = new ObjectMapper();
//        User u2 = mapper.readValue( result, User.class );
//        assertTrue( u2.getPassword() == null );
//        assertNotNull( result );
//    }

    @Test
    public void objectToJsonValidateDataTest() throws Exception {
        User u = createOrganizationAdminUser();
        String result = Helpers.objectToJson( u );

        ObjectMapper mapper = new ObjectMapper();
        User u2 = mapper.readValue( result, User.class );

        assertTrue( u2.getAuthority().get(0).getPermission().equals( u.getAuthority().get(0).getPermission() ) );
        assertTrue( u2.getLastName().equals( u.getLastName() ) );
        assertTrue( u2.getOrganization().equals( u.getOrganization() ) );
        assertTrue( u2.getLastUpdated() == u.getLastUpdated() );
        assertTrue( u2.getEmail().equals( u.getEmail() ) );
        assertTrue( u2.getFirstName().equals( u.getFirstName() ) );
        assertNull( u2.get_id() );
        assertTrue( u2.getIdSecurityNumber() == u.getIdSecurityNumber() );
        assertTrue( u2.isEnabled() == u.isEnabled() );
        assertTrue( u2.getPasswordUpdateDate() == u.getPasswordUpdateDate() );
        assertNotNull( result );
    }

    @Test
    public void passwordEncoderTest() throws Exception {
        String result = Helpers.passwordEncoder( "admin" );
        assertTrue( BCrypt.checkpw( "admin", result ) );
        System.out.println( result );
    }
}