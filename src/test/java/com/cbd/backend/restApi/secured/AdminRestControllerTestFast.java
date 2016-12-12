package com.cbd.backend.restApi.secured;

import com.cbd.backend.database.OrganizationRepository;
import com.cbd.backend.database.UserRepository;
import com.cbd.backend.model.dbo.Organization;
import com.cbd.backend.model.dbo.User;
import com.cbd.backend.service.impl.DataAccessServiceImpl;
import com.cbd.backend.service.impl.OrganizationServiceImpl;
import com.cbd.backend.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.WriteResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.*;

import static com.cbd.backend.TestFactories.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith( MockitoJUnitRunner.class )
public class AdminRestControllerTestFast {

    private OrganizationServiceImpl organizationService;
    private UserServiceImpl userService;
    private DataAccessServiceImpl dataAccessService;
    private ObjectMapper mapper;
    private AdminRestController adminRestController;

    @Mock
    private OrganizationRepository organizationRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private MongoTemplate mongoTemplate;

    @Mock
    HttpServletRequest httpServletRequest;

    @Before
    public void init() {
        adminRestController = new AdminRestController();
        userService = new UserServiceImpl();
        organizationService = new OrganizationServiceImpl();
        dataAccessService = new DataAccessServiceImpl();
        organizationService.setDataAccessService( dataAccessService );
        dataAccessService.setOrganizationRepository( organizationRepository );
        dataAccessService.setUserRepository( userRepository );
        mapper = new ObjectMapper();
        userService.setDataAccessService( dataAccessService );
        organizationService.setDataAccessService( dataAccessService );
        ReflectionTestUtils.setField( adminRestController, "organizationService", organizationService );
        //dataAccessService.setMongoTemplate( mongoTemplate );
    }

    @Test
    public void adminPermanentlyDeleteOrganization() {
        ResponseEntity<?> response = adminRestController.permanentDeleteOrganization( httpServletRequest
        , "TestOrganization");

        when(organizationRepository.findAllByOrganizationName( any(String.class))).thenReturn( new ArrayList<Organization>());
//        when(organizationRepository.delete( any( List.class ) ))
        assertTrue( response.getBody().equals( true ) );

    }

    @Test
    public void adminPermanentlyDeleteUser() {
        ResponseEntity<?> response = adminRestController.permanentDeleteOrganization( httpServletRequest
                , "newUser" );


        when(userRepository.findAllByUsername( any(String.class))).thenReturn( new ArrayList<User>());

        assertTrue( response.getBody().equals( true ) );

    }
}