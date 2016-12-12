package com.cbd.backend.restApi.secured;

import com.cbd.backend.TestFactories;
import com.cbd.backend.common.Helpers;
import com.cbd.backend.model.dbo.Organization;
import com.cbd.backend.model.dbo.User;
import com.cbd.backend.restApi.RestHelpers;
import com.cbd.backend.service.impl.JwtUserDetailsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mobile.device.FunctionalTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.cbd.backend.TestFactories.getNewUser;
import static com.cbd.backend.restApi.RestHelpers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class OrganizationRestControllerSlow extends FunctionalTest {

    @Autowired
    JwtUserDetailsServiceImpl jwtUserDetailsService;

    @Test
    public void createNewOrganizationSlow() throws Exception {

        User user = getNewUser();

        //Setup
        final String requestBody = Helpers.objectToJson( user );
        final String organizationUserAuthBody = authBodyInJson( signUpUserName );
        String signUpToken = getAuthenticationToken( organizationUserAuthBody );


        String token = generateToken( user.getUsername(), null);
        Organization organization = TestFactories.createOrganization( "TestOrganization" );

        //cleanup old results if there was old artifacts in db
        String adminToken = generateToken( "admin" );
        deleteOrganization( organization, adminToken );
        adminDeleteUser( getNewUser().getUsername(), adminToken );

        //Execute & verify
        RestHelpers.createNewUserFromAPI( requestBody, signUpToken );
        createOrganization( organization, token );

        //cleanup after test
        deleteOrganization( organization, adminToken );
        adminDeleteUser( getNewUser().getUsername(), adminToken );

    }
}