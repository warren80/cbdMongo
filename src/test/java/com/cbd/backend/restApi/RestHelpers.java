package com.cbd.backend.restApi;


import static com.cbd.backend.TestFactories.getNewUser;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.cbd.backend.common.Helpers;
import com.cbd.backend.model.dbo.Organization;

public class RestHelpers {
    public static String signUpUserName = "signUp";
    public static String signUpPassword = "signUp";
    private static String password = "vA!Id001";

    public static void createNewUserFromAPI( String requestBody, String token) {
        given()
                .contentType( "application/json" )
                .header( "Authorization", token )
                .body( requestBody )
                .when()
                .post( "/api/users" )
                .then()
                .body( "username", equalTo( getNewUser().getUsername() ) );
    }

    public static void createNewUserFromAPI( String requestBody, String token, String userName) {
        given()
                .contentType( "application/json" )
                .header( "Authorization", token )
                .body( requestBody )
                .when()
                .post( "/api/users" )
                .then()
                .body( "username", equalTo( userName ) );
    }



    public static void createNewOrganization(String organizationAsString, String username, String token) {
        given()
                .contentType( "application/json" )
                .header( "Authorization", token )
                .body( organizationAsString )
                .when()
                .post( "/api/users/{userName}", username )
                .then()
                .body( "username", equalTo( getNewUser().getUsername() ) );
        //Now cleanup
    }

    public static void createOrganization(Organization organization , String token) {
        String body = Helpers.objectToJson( organization );
        given()
                .contentType( "application/json" )
                .header( "Authorization", token )
                .body( body )
                .when()
                .post( "/api/createOrganization" )
                .then()
                .body( "organizationName", equalTo( organization.getOrganizationName() ) );
        //Cleanup database
    }

    public static void deleteOrganization( Organization organization, String token) {
        given()
                .contentType( "application/json" )
                .header( "Authorization", token )
                .when()
                .delete( "/api/admin/deleteOrganization/{organizationName}", organization.getOrganizationName() )
                .then();
    }

    public static void adminDeleteUser( String username, String token ) {
        given()
                .contentType( "application/json" )
                .header( "Authorization", token )
                .when()
                .delete("/api/admin/deleteUser/{userName}", username )
                .then();
    }

    public static String authBodyInJson( String username ) {
        String pass;
        if ( username.equals( signUpUserName )) {
            pass = signUpPassword;
        } else {
            pass = password;
        }
        return  " { \"username\": \"" + username + "\","
                + "\"password\": \"" + pass + "\" }";
    }
}
