package com.cbd.backend.api;

import com.cbd.backend.common.Helpers;
import com.cbd.backend.model.NewAccount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mobile.device.FunctionalTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.cbd.backend.model.NewUserTestFast.getNewAccount;
import static com.cbd.backend.model.UserTestFast.getNewUser;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AccountRestControllerSlow extends FunctionalTest {

    @Test
    public void createNewAccountSlow() throws Exception {

        NewAccount account = getNewAccount();
        String body = Helpers.objectToJson( account );
        //final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername( new String( "newUser" ) );
//        String token =  jwtTokenUtil.generateToken( userDetails, device );
        given()
                .contentType( "application/json" )
                .body( body )
                .when()
                .post( "/api/createAccount" )
                .then()
                .body( "accountName", equalTo( account.getAccountName() ) );
    }

}