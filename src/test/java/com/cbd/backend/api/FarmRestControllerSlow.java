package com.cbd.backend.api;

import com.cbd.backend.common.Helpers;
import com.cbd.backend.model.NewFarm;
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
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class FarmRestControllerSlow extends FunctionalTest {

    @Autowired
    JwtUserDetailsServiceImpl jwtUserDetailsService;

    @Test
    public void createNewFarmSlow() throws Exception {

        NewFarm farm = getNewFarm();
        String body = Helpers.objectToJson( farm );
        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername( new String( "newUser" ) );
//        String token =  jwtTokenUtil.generateToken( userDetails, device );
        given()
                .contentType( "application/json" )
                .body( body )
                .when()
                .post( "/api/createFarm" )
                .then()
                .body( "farmName", equalTo( farm.getFarmName() ) );


    }

}