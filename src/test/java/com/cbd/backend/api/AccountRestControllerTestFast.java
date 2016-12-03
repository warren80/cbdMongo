package com.cbd.backend.api;

import com.cbd.backend.restApi.unsecured.AccountRestController;
import com.cbd.backend.common.model.AccountValidation;
import com.cbd.backend.database.AccountRepository;
import com.cbd.backend.database.UserRepository;
import com.cbd.backend.model.Account.dbo.Account;
import com.cbd.backend.model.Measurements;
import com.cbd.backend.model.NewAccount;
import com.cbd.backend.model.dbo.FarmPlotScheme;
import com.cbd.backend.model.dbo.User;
import com.cbd.backend.service.impl.AccountServiceImpl;
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

import java.util.Locale;

import static com.cbd.backend.TestFactories.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith( MockitoJUnitRunner.class )
public class AccountRestControllerTestFast {

    private AccountRestController accountRestController;
    private AccountServiceImpl accountService;
    private UserServiceImpl userService;
    private DataAccessServiceImpl dataAccessService;
    private ObjectMapper mapper;

    @Mock
    private UserRepository userRepository;
    @Mock
    private AccountRepository accountRepository;

    @Before
    public void init() {
        mapper = new ObjectMapper();
        accountService = new AccountServiceImpl();
        accountRestController = new AccountRestController();
        userService = new UserServiceImpl();
        dataAccessService = new DataAccessServiceImpl();
        dataAccessService.setUserRepository( userRepository );
        dataAccessService.setAccountRepository( accountRepository );
        userService.setDataAccessService( dataAccessService );
        accountService.setDataAccessService( dataAccessService );

        ReflectionTestUtils.setField( accountRestController, "accountService", accountService );
        ReflectionTestUtils.setField( accountRestController, "userService", userService );
    }

    @Test
    public void createAccount() throws Exception {
        NewAccount account = getNewAccount();
        when( accountRepository.save( any( Account.class ) ) ).thenReturn( getNewAccount() );
        when( userRepository.save( any( User.class ) ) ).thenReturn( getStockUser() );
        when( userRepository.findFirstByUsername( any ( String.class) ) ).thenReturn( null );
        when( accountRepository.findFirstByAccountName( any ( String.class) ) ).thenReturn( null );

        ResponseEntity<?> response = accountRestController.createAccount( account );
        NewAccount body = (NewAccount) response.getBody();
        assertTrue( body.getAccountName().equals( "TestAccount" ) );
    }

    @Test
    public void usernameTaken() throws Exception {
        NewAccount account = getNewAccount();
        when( accountRepository.findFirstByAccountName( any ( String.class) ) ).thenReturn( null );
        when( userRepository.findTopByUsernameOrderByLastUpdatedDesc( any ( String.class) ) ).thenReturn( getStockUser() );
        ResponseEntity<?> response = accountRestController.createAccount( account );

        AccountValidation accountValidation = (AccountValidation) response.getBody();
        assertTrue( accountValidation.isUsernameValid() == false );
    }

    @Test
    public void accountNameTaken() throws Exception {
        NewAccount account = getNewAccount();
        when( accountRepository.findFirstByAccountName( any ( String.class) ) ).thenReturn( getNewAccount() );
        when( userRepository.save( any( User.class ) ) ).thenReturn( null );
        when( userRepository.findFirstByUsername( any ( String.class) ) ).thenReturn( null);
        ResponseEntity<?> response = accountRestController.createAccount( account );

        AccountValidation accountValidation = (AccountValidation) response.getBody();
        assertTrue( accountValidation.isValidAccountName() == false );
    }

    @Test
    public void newAccountFailBadPhoneNumber() {
        NewAccount account = getNewAccount();
        account.setAccountPhone(null);

        when( accountRepository.save( any( Account.class ) ) ).thenReturn( null );
        when( userRepository.save( any( User.class ) ) ).thenReturn( null );
        when( userRepository.findFirstByUsername( any ( String.class) ) ).thenReturn( null);
        when( accountRepository.findFirstByAccountName( any( String.class) ) ).thenReturn( null );

        ResponseEntity<?> response = accountRestController.createAccount( account );
        AccountValidation accountValidation = (AccountValidation) response.getBody();

        assertFalse( accountValidation.isValidAccountPhone() );
    }
    @Test
    public void newAccountFailBadMeasurementType() {
        NewAccount account = getNewAccount();
        account.setMeasurements( null );


        when( accountRepository.save( any( Account.class ) ) ).thenReturn( null );
        when( userRepository.save( any( User.class ) ) ).thenReturn( null );
        when( userRepository.findFirstByUsername( any ( String.class) ) ).thenReturn( null) ;
        when( accountRepository.findFirstByAccountName( any( String.class) ) ).thenReturn( null );

        ResponseEntity<?> response = accountRestController.createAccount( account );
        AccountValidation accountValidation = (AccountValidation) response.getBody();

        assertFalse( accountValidation.isValidMeasurements() );
    }

    @Test
    public void newAccountFailBadSecondaryPhoneNumber() {
        NewAccount account = getNewAccount();
        account.setSecondaryAccountPhone( "23" );

        when( accountRepository.save( any( Account.class ) ) ).thenReturn( null );
        when( userRepository.save( any( User.class ) ) ).thenReturn( null );
        when( userRepository.findFirstByUsername( any ( String.class) ) ).thenReturn( null);
        when( accountRepository.findFirstByAccountName( any( String.class) ) ).thenReturn( null );

        ResponseEntity<?> response = accountRestController.createAccount( account );
        AccountValidation accountValidation = (AccountValidation) response.getBody();

        assertFalse( accountValidation.isValidSecondaryPhoneNumber() );
    }

    private Account testAccount() {
        long timestamp = System.currentTimeMillis();
        Account account = new NewAccount();
        account.setSubscriptionEndDate( 1L );
        account.setMeasurements( Measurements.METRIC );
        account.setLocale( new Locale( "en", "CA" ) );
        account.setLastUpdated( timestamp );
        account.setAccountName( "testAccount" );
        account.setEnabled( true );
        account.setFarmPlotScheme( FarmPlotScheme.AREA );
        return account;
    }

}