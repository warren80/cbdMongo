package com.cbd.backend.api;

import com.cbd.backend.model.dbo.Farm;
import com.cbd.backend.model.dbo.LanguageAndCountry;
import com.cbd.backend.model.NewFarm;
import com.cbd.backend.restApi.unsecured.FarmRestController;
import com.cbd.backend.common.model.FarmValidation;
import com.cbd.backend.database.FarmRepository;
import com.cbd.backend.database.UserRepository;
import com.cbd.backend.model.Measurements;
import com.cbd.backend.model.dbo.FarmPlotScheme;
import com.cbd.backend.model.dbo.User;
import com.cbd.backend.service.impl.FarmServiceImpl;
import com.cbd.backend.service.impl.DataAccessServiceImpl;
import com.cbd.backend.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import static com.cbd.backend.TestFactories.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith( MockitoJUnitRunner.class )
public class FarmRestControllerTestFast {

    private FarmRestController farmRestController;
    private FarmServiceImpl farmService;
    private UserServiceImpl userService;
    private DataAccessServiceImpl dataAccessService;
    private ObjectMapper mapper;

    @Mock
    private UserRepository userRepository;
    @Mock
    private FarmRepository farmRepository;

    @Before
    public void init() {
        mapper = new ObjectMapper();
        farmService = new FarmServiceImpl();
        farmRestController = new FarmRestController();
        userService = new UserServiceImpl();
        dataAccessService = new DataAccessServiceImpl();
        dataAccessService.setUserRepository( userRepository );
        dataAccessService.setFarmRepository(farmRepository);
        userService.setDataAccessService( dataAccessService );
        farmService.setDataAccessService( dataAccessService );

        ReflectionTestUtils.setField(farmRestController, "farmService", farmService);
        ReflectionTestUtils.setField(farmRestController, "userService", userService );
    }

    @Test
    public void createFarm() throws Exception {
        NewFarm farm = getNewFarm();
        when( farmRepository.save( any( Farm.class ) ) ).thenReturn( getNewFarm() );
        when( userRepository.save( any( User.class ) ) ).thenReturn( createFarmAdminUser() );
        when( userRepository.findFirstByUsername( any ( String.class) ) ).thenReturn( null );
        when( farmRepository.findFirstByFarmName( any ( String.class) ) ).thenReturn( null );

        ResponseEntity<?> response = farmRestController.createFarm( farm );
        NewFarm body = (NewFarm) response.getBody();
        assertTrue( body.getFarmName().equals( "TestFarm" ) );
    }

    @Test
    public void usernameTaken() throws Exception {
        NewFarm farm = getNewFarm();
        when( farmRepository.findFirstByFarmName( any ( String.class) ) ).thenReturn( null );
        when( userRepository.findTopByUsernameOrderByLastUpdatedDesc( any ( String.class) ) ).thenReturn( createFarmAdminUser() );
        ResponseEntity<?> response = farmRestController.createFarm( farm );

        FarmValidation farmValidation = (FarmValidation) response.getBody();
        assertTrue( farmValidation.isUsernameValid() == false );
    }

    @Test
    public void farmNameTaken() throws Exception {
        NewFarm farm = getNewFarm();
        when( farmRepository.findFirstByFarmName( any ( String.class) ) ).thenReturn( getNewFarm() );
        when( userRepository.save( any( User.class ) ) ).thenReturn( null );
        when( userRepository.findFirstByUsername( any ( String.class) ) ).thenReturn( null);
        ResponseEntity<?> response = farmRestController.createFarm( farm );

        FarmValidation farmValidation = (FarmValidation) response.getBody();
        assertTrue( farmValidation.isValidFarmName() == false );
    }

    @Test
    public void newFarmFailBadPhoneNumber() {
        NewFarm farm = getNewFarm();
        farm.setFarmPhone(null);

        when( farmRepository.save( any( Farm.class ) ) ).thenReturn( null );
        when( userRepository.save( any( User.class ) ) ).thenReturn( null );
        when( userRepository.findFirstByUsername( any ( String.class) ) ).thenReturn( null);
        when( farmRepository.findFirstByFarmName( any( String.class) ) ).thenReturn( null );

        ResponseEntity<?> response = farmRestController.createFarm( farm );
        FarmValidation farmValidation = (FarmValidation) response.getBody();

        assertFalse( farmValidation.isValidFarmPhone() );
    }
    @Test
    public void newFarmFailBadMeasurementType() {
        NewFarm farm = getNewFarm();
        farm.setMeasurements( null );


        when( farmRepository.save( any( Farm.class ) ) ).thenReturn( null );
        when( userRepository.save( any( User.class ) ) ).thenReturn( null );
        when( userRepository.findFirstByUsername( any ( String.class) ) ).thenReturn( null) ;
        when( farmRepository.findFirstByFarmName( any( String.class) ) ).thenReturn( null );

        ResponseEntity<?> response = farmRestController.createFarm( farm );
        FarmValidation farmValidation = (FarmValidation) response.getBody();

        assertFalse( farmValidation.isValidMeasurements() );
    }

    @Test
    public void newFarmFailBadSecondaryPhoneNumber() {
        NewFarm farm = getNewFarm();
        farm.setSecondaryFarmPhone( "23" );

        when( farmRepository.save( any( Farm.class ) ) ).thenReturn( null );
        when( userRepository.save( any( User.class ) ) ).thenReturn( null );
        when( userRepository.findFirstByUsername( any ( String.class) ) ).thenReturn( null);
        when( farmRepository.findFirstByFarmName( any( String.class) ) ).thenReturn( null );

        ResponseEntity<?> response = farmRestController.createFarm( farm );
        FarmValidation farmValidation = (FarmValidation) response.getBody();

        assertFalse( farmValidation.isValidSecondaryPhoneNumber() );
    }

    private Farm testFarm() {
        long timestamp = System.currentTimeMillis();
        Farm farm = new NewFarm();
        farm.setSubscriptionEndDate( 1L );
        farm.setMeasurements( Measurements.METRIC );
        farm.setLanguageAndCountry( new LanguageAndCountry( "en", "CA", null ) );
        farm.setLastUpdated( timestamp );
        farm.setFarmName( "testFarm" );
        farm.setEnabled( true );
        farm.setFarmPlotScheme( FarmPlotScheme.AREA );
        return farm;
    }

}