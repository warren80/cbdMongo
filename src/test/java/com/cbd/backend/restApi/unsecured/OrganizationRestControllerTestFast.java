package com.cbd.backend.restApi.unsecured;

import com.cbd.backend.TestFactories;
import com.cbd.backend.model.Factory.JwtUserFactory;
import com.cbd.backend.model.JwtUser;
import com.cbd.backend.model.dbo.FarmPlotScheme;
import com.cbd.backend.model.dbo.Organization;
import com.cbd.backend.model.dbo.LanguageAndCountry;
import com.cbd.backend.common.model.OrganizationValidation;
import com.cbd.backend.database.OrganizationRepository;
import com.cbd.backend.database.UserRepository;
import com.cbd.backend.model.Measurements;
import com.cbd.backend.model.dbo.User;
import com.cbd.backend.restApi.secured.OrganizationRestController;
import com.cbd.backend.security.JwtTokenUtil;
import com.cbd.backend.service.impl.JwtUserDetailsServiceImpl;
import com.cbd.backend.service.impl.OrganizationServiceImpl;
import com.cbd.backend.service.impl.DataAccessServiceImpl;
import com.cbd.backend.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.FunctionalTest;
import org.springframework.test.util.ReflectionTestUtils;

import static com.cbd.backend.TestFactories.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith( MockitoJUnitRunner.class )
public class OrganizationRestControllerTestFast {

    private OrganizationRestController organizationRestController;
    private OrganizationServiceImpl organizationService;
    private UserServiceImpl userService;
    private DataAccessServiceImpl dataAccessService;
    private ObjectMapper mapper;
    private JwtTokenUtil jwtTokenUtil;

    @Mock
    private UserRepository userRepository;
    @Mock
    private OrganizationRepository organizationRepository;

    @Before
    public void init() {
        jwtTokenUtil = new JwtTokenUtil();
        ReflectionTestUtils.setField( jwtTokenUtil, "secret", "mySecret" );
        ReflectionTestUtils.setField( jwtTokenUtil, "expiration", 86400000L );



        mapper = new ObjectMapper();
        organizationService = new OrganizationServiceImpl();
        organizationRestController = new OrganizationRestController();
        userService = new UserServiceImpl();
        dataAccessService = new DataAccessServiceImpl();
        dataAccessService.setUserRepository( userRepository );
        dataAccessService.setOrganizationRepository(organizationRepository);
        userService.setDataAccessService( dataAccessService );
        organizationService.setDataAccessService( dataAccessService );

        ReflectionTestUtils.setField( organizationRestController, "organizationService", organizationService);
        ReflectionTestUtils.setField( organizationRestController, "userService", userService );
        ReflectionTestUtils.setField( organizationRestController, "jwtTokenUtil", jwtTokenUtil );
    }

    @Test
    public void createOrganization() throws Exception {
        Organization organization = TestFactories.createOrganization();
        when( organizationRepository.save( any( Organization.class ) ) ).thenReturn( TestFactories.createOrganization() );

        when( userRepository.findFirstByUsername( any ( String.class) ) ).thenReturn( null );
        when( organizationRepository.findFirstByOrganizationName( any ( String.class) ) ).thenReturn( null );
        when( userRepository.findTopByUsernameOrderByLastUpdatedDesc( any ( String.class ) ) ).thenReturn( createOrganizationAdminUser() );
        when( userRepository.save( any( User.class ) ) ).thenReturn( createOrganizationAdminUser() );


        String token = createToken( "admin", null );

        ResponseEntity<?> response = organizationRestController.createOrganization( token, organization );
        Organization body = (Organization) response.getBody();
        assertTrue( body.getOrganizationName().equals( "TestOrganization" ) );
    }

    @Test
    public void usernameTaken() throws Exception {
        Organization organization = TestFactories.createOrganization();
        when( organizationRepository.findFirstByOrganizationName( any ( String.class) ) ).thenReturn( null );
        when( userRepository.findTopByUsernameOrderByLastUpdatedDesc( any ( String.class) ) ).thenReturn( createOrganizationAdminUser() );

        String token = createToken( "admin", "TestOrganization" );
        ResponseEntity<?> response = organizationRestController.createOrganization( token, organization );

        OrganizationValidation organizationValidation = (OrganizationValidation) response.getBody();
//        assertTrue( organizationValidation.isUsernameValid() == false );
    }

    @Test
    public void organizationNameTaken() throws Exception {
        Organization organization = TestFactories.createOrganization();
        when( organizationRepository.findFirstByOrganizationName( any ( String.class) ) ).thenReturn( TestFactories.createOrganization() );
        when( userRepository.save( any( User.class ) ) ).thenReturn( null );
        when( userRepository.findFirstByUsername( any ( String.class) ) ).thenReturn( null);
        String token = createToken( "admin", null );
        ResponseEntity<?> response = organizationRestController.createOrganization( token, organization );

        OrganizationValidation organizationValidation = (OrganizationValidation) response.getBody();
        assertTrue( organizationValidation.isValidOrganizationName() == false );
    }

    @Test
    public void newOrganizationFailBadPhoneNumber() {
        Organization organization = TestFactories.createOrganization();
        organization.setOrganizationPhone(null);

        when( organizationRepository.save( any( Organization.class ) ) ).thenReturn( null );
        when( userRepository.save( any( User.class ) ) ).thenReturn( null );
        when( userRepository.findFirstByUsername( any ( String.class) ) ).thenReturn( null);
        when( organizationRepository.findFirstByOrganizationName( any( String.class) ) ).thenReturn( null );
        String token = createToken( "admin", null );
        ResponseEntity<?> response = organizationRestController.createOrganization( token, organization );
        OrganizationValidation organizationValidation = (OrganizationValidation) response.getBody();

        assertFalse( organizationValidation.isValidOrganizationPhone() );
    }
    @Test
    public void newOrganizationFailBadMeasurementType() {
        Organization organization = TestFactories.createOrganization();
        organization.setMeasurements( null );


        when( organizationRepository.save( any( Organization.class ) ) ).thenReturn( null );
        when( userRepository.save( any( User.class ) ) ).thenReturn( null );
        when( userRepository.findFirstByUsername( any ( String.class) ) ).thenReturn( null) ;
        when( organizationRepository.findFirstByOrganizationName( any( String.class) ) ).thenReturn( null );
        String token = createToken( "admin", null );

        ResponseEntity<?> response = organizationRestController.createOrganization( token, organization );
        OrganizationValidation organizationValidation = (OrganizationValidation) response.getBody();

        assertFalse( organizationValidation.isValidMeasurements() );
    }

    @Test
    public void newOrganizationFailBadSecondaryPhoneNumber() {
        Organization organization = TestFactories.createOrganization();
        organization.setSecondaryOrganizationPhone( "23" );

        when( organizationRepository.save( any( Organization.class ) ) ).thenReturn( null );
        when( userRepository.save( any( User.class ) ) ).thenReturn( null );
        when( userRepository.findFirstByUsername( any ( String.class) ) ).thenReturn( null);
        when( organizationRepository.findFirstByOrganizationName( any( String.class) ) ).thenReturn( null );
        String token = createToken( "admin", null );
        ResponseEntity<?> response = organizationRestController.createOrganization( token, organization );
        OrganizationValidation organizationValidation = (OrganizationValidation) response.getBody();

        assertFalse( organizationValidation.isValidSecondaryPhoneNumber() );
    }

    private Organization testOrganization() {
        long timestamp = System.currentTimeMillis();
        Organization organization = new Organization();
        organization.setSubscriptionEndDate( 1L );
        organization.setMeasurements( Measurements.METRIC );
        organization.setLanguageAndCountry( new LanguageAndCountry( "en", "CA", null ) );
        organization.setLastUpdated( timestamp );
        organization.setOrganizationName( "testOrganization" );
        organization.setEnabled( true );
        organization.setFarmPlotScheme( FarmPlotScheme.AREA );
        return organization;
    }
    private String createToken( String username, String organization ) {
        User user = JwtUserDetailsServiceImpl.createAdminSystemUser( username,
                "password",
                "admin",
                "admin",
                "admin@email.com");
        user.setOrganization( organization );
        JwtUser jwtUser= JwtUserFactory.create( user );
        return jwtTokenUtil.generateToken( jwtUser, FunctionalTest.device );
    }
}