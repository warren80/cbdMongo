package com.cbd.backend.restApi.secured;

import com.cbd.backend.TestFactories;
import com.cbd.backend.common.Helpers;
import com.cbd.backend.model.dbo.User;
import com.cbd.backend.restApi.RestHelpers;
import com.cbd.backend.security.JwtTokenUtil;
import com.cbd.backend.service.impl.JwtUserDetailsServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mobile.device.FunctionalTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.cbd.backend.TestFactories.*;
import static com.cbd.backend.restApi.RestHelpers.*;
import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserRestControllerTestSlow extends FunctionalTest {

    @Autowired
    JwtUserDetailsServiceImpl jwtUserDetailsService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;




    @Value( "${api.users} ")
    String userEndpoint;

    @Before
    void init() {

    }

    @Test
    public void createNewOrganizationAdminUserSlow() {


        String requestBody = Helpers.objectToJson( getNewUser() );
        final String organizationUserAuthBody = authBodyInJson( signUpUserName );
        String signUpToken = getAuthenticationToken( organizationUserAuthBody );

        RestHelpers.createNewUserFromAPI( requestBody, signUpToken);

        RestHelpers.adminDeleteUser( getNewUser().getUsername(), generateToken( "admin" ) );
    }



    private class Token {
        private String token;
        public void setToken( String token ) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }
    }

    @Test
    public void create2UsersIn1OrgUserTestSlow() {
        String createUser = "TestUser";
        String signUpToken = getAuthenticationToken( authBodyInJson( signUpUserName ) );
        User userOrgAdmin = getNewUser();
        String signUpUser = Helpers.objectToJson( userOrgAdmin );
        String token = getAuthenticationToken( authBodyInJson( createUser ) );
        //Create a new organization user.
        RestHelpers.createNewUserFromAPI( signUpUser, signUpToken );

        //TODO create organization and set user organization value (2 db calls)
        String organizationName = "TestOrg";
        createNewOrganization( organizationName, createUser, token );

        //TODO create a second regular user ( return random password ) with password exipired
        ;
        String secondUserRequestBody = Helpers.objectToJson( TestFactories.getNewUser( createUser ) );
        RestHelpers.createNewUserFromAPI( secondUserRequestBody, token, createUser );

        // cleanup




        //Run a get on user just created
        given()
                .contentType( "application/json" )
                .header( "Authorization", token )
                .when()
                .get( "/api/users/{userName}", getNewUser().getUsername() )
                .then()
                .body( "username", equalTo( getNewUser().getUsername() ) );
        //Now cleanup
        RestHelpers.adminDeleteUser( getNewUser().getUsername(), generateToken( "admin" ) );
    }

//    @Test
//    public void getAuthenticatedUserSlow() throws Exception {
//
//    }






    @Test
    public void updateUser() throws Exception {

    }
}