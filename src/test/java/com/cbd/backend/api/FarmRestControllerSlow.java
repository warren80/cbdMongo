package com.cbd.backend.api;

import com.cbd.backend.common.Helpers;
import com.cbd.backend.model.NewFarm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mobile.device.FunctionalTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.cbd.backend.TestFactories.*;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class FarmRestControllerSlow extends FunctionalTest {

    @Test
    public void createNewFarmSlow() throws Exception {

        NewFarm farm = getNewFarm();
        String body = Helpers.objectToJson( farm );
        //final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername( new String( "newUser" ) );
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