package com.cbd.backend.security;

import com.cbd.backend.model.JwtUser;
import com.cbd.backend.model.dbo.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.*;
import static org.springframework.mobile.device.FunctionalTest.device;

@RunWith( MockitoJUnitRunner.class )
public class SecurityLevelTest {

    JwtTokenUtil jwtTokenUtil;
    SecurityLevel securityLevel;

    @Before
    public void init() {
        jwtTokenUtil = new JwtTokenUtil();
        securityLevel = new SecurityLevel();
        ReflectionTestUtils.setField( jwtTokenUtil, "expiration", 3600000L );
        ReflectionTestUtils.setField( jwtTokenUtil, "secret", "mySecret" );
        ReflectionTestUtils.setField( securityLevel, "jwtTokenUtil", jwtTokenUtil );
    }

    @Test
    public void isReadonlyUserFast() throws Exception {
        User user = JwtTokenUtilTest.createRegularSystemUser();
        JwtUser jwtUser = JwtTokenUtilTest.createJwtUser( user );

        String token =  jwtTokenUtil.generateToken( jwtUser, device );
        assertTrue( securityLevel.isReadonlyUser( user.getOrganization(), token ) );
        assertFalse( securityLevel.isAdmin( token ) );
        assertFalse( securityLevel.isPermittedToUpdatingInventory( user.getOrganization(), user.getUsername(), token ) );
        assertFalse( securityLevel.isPermittedToUpdatingInventory( user.getOrganization(), user.getUsername(), token) );
    }

    @Test
    public void isAdminTestFast() throws Exception {
        User user = JwtTokenUtilTest.createAdmin();
        JwtUser jwtUser = JwtTokenUtilTest.createJwtUser( user );

        String token =  jwtTokenUtil.generateToken( jwtUser, device );
        assertTrue( securityLevel.isAdmin( token ) );
        assertTrue( securityLevel.isOrganizationAdmin( null, token) );
        assertTrue( securityLevel.isReadonlyUser( null, token ) );
        assertTrue( securityLevel.isPermittedToUpdatingInventory( null, null, token ) );
    }

    @Test
    public void isFarmAdminTestFast() throws Exception {
        User user = JwtTokenUtilTest.createAdmin();
        JwtUser jwtUser = JwtTokenUtilTest.createJwtUser( user );

        String token =  jwtTokenUtil.generateToken( jwtUser, device );
        assertTrue( securityLevel.isAdmin( token ) );
    }

    @Test
    public void isPermittedToUpdatingInventoryFast() throws Exception {
        User user = JwtTokenUtilTest.createInventoryUpdateUser();
        JwtUser jwtUser = JwtTokenUtilTest.createJwtUser( user );

        String token =  jwtTokenUtil.generateToken( jwtUser, device );
        assertFalse( securityLevel.isAdmin( token ) );
        assertFalse( securityLevel.isOrganizationAdmin( user.getOrganization(), token) );
        assertTrue( securityLevel.isReadonlyUser( user.getOrganization(), token ) );
        assertTrue( securityLevel.isPermittedToUpdatingInventory( user.getOrganization(), user.getUsername(), token ) );
    }

    @Test
    public void isOrganizationAdminFast() throws Exception {
        User user = JwtTokenUtilTest.createOrganizationAdminUser();
        JwtUser jwtUser = JwtTokenUtilTest.createJwtUser( user );

        String token = jwtTokenUtil.generateToken( jwtUser, device );
        assertFalse( securityLevel.isAdmin( token ) );
        assertTrue( securityLevel.isOrganizationAdmin( user.getOrganization(), token) );
        assertTrue( securityLevel.isReadonlyUser( user.getOrganization(), token ) );
        assertTrue( securityLevel.isPermittedToUpdatingInventory( user.getOrganization(), user.getUsername(), token ) );
    }

    @Test
    public void isAdmin() throws Exception {

    }





}