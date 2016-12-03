package com.cbd.backend.api;

import com.cbd.backend.common.Helpers;
import com.cbd.backend.model.JwtUser;
import com.cbd.backend.security.JwtTokenUtil;
import com.cbd.backend.service.impl.JwtUserDetailsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mobile.device.FunctionalTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import static com.cbd.backend.TestFactories.*;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserRestControllerTestSlow extends FunctionalTest {

//    @Before
//    public void token() {
//        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername( new String( "admin" ) );
//        String token =  new JwtTokenUtil().generateToken( userDetails, device );
//    }

    @Autowired
    JwtUserDetailsServiceImpl jwtUserDetailsService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Test
    public void getAuthenticatedUser() throws Exception {

        String body = Helpers.objectToJson( getNewUser() );
        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername( new String( "newUser" ) );
        String token =  jwtTokenUtil.generateToken( (JwtUser) userDetails, device );

        given()
                .contentType( "application/json" )
                .header( "Authorization", token )
                .body( body )
                .when()
                .post( "/api/users" )
                .then()
                .body( "username", equalTo( getNewUser().getUsername() ) );

        given()
                .contentType( "application/json" )
                .header( "Authorization", token )
                .when()
                .delete("/api/users" )
                .then()
                .body("username", equalTo( getNewUser().getUsername() ) ).body( "enabled", equalTo( false ) );
    }

    @Test
    public void updateUser() throws Exception {

    }
}