package org.springframework.mobile.device;

import com.cbd.backend.model.Factory.JwtUserFactory;
import com.cbd.backend.model.JwtUser;
import com.cbd.backend.model.dbo.User;
import com.cbd.backend.security.JwtTokenUtil;
import com.cbd.backend.service.impl.JwtUserDetailsServiceImpl;
import com.jayway.restassured.RestAssured;
import org.junit.BeforeClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import static com.cbd.backend.TestFactories.getNewUser;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.Is.isA;

public class FunctionalTest {

    public static LiteDevice device = LiteDevice.NORMAL_INSTANCE;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @BeforeClass
    public static void setup() {
        String port = System.getProperty("server.port");
        if (port == null) {
            RestAssured.port = 8080;
        } else {
            RestAssured.port = Integer.valueOf(port);
        }

        String basePath = System.getProperty("server.base");
        if (basePath == null) {
            basePath = "/";
        }
        RestAssured.basePath = basePath;

        String baseHost = System.getProperty("server.host");
        if (baseHost == null) {
            baseHost = "http://localhost";
        }
        RestAssured.baseURI = baseHost;
    }

    public String generateToken( String username ) {
        User user = getNewUser( username );
        String token = jwtTokenUtil.generateToken( JwtUserFactory.create( user ), device);
        return token;
    }


    public String generateToken( String username, String org ) {
        User user = getNewUser( username, org);
        String token = jwtTokenUtil.generateToken( JwtUserFactory.create( user ), device);
        return token;
    }

    public String generateToken( String username, String org, String pass ) {
        User user = getNewUser( username, org, pass);
        String token = jwtTokenUtil.generateToken( JwtUserFactory.create( user ), device);
        return token;
    }


    public String getAuthenticationToken( String body ) {
        String token = given()
                .contentType("application/json")
                .body(body)
                .when()
                .post("/api/auth")
                .then()
                .body("token", isA(String.class))
                .extract()
                .path("token");
        return token;
    }

}