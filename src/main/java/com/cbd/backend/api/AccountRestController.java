package com.cbd.backend.api;

import com.cbd.backend.common.Helpers;
import com.cbd.backend.common.model.AccountValidation;
import com.cbd.backend.model.Account.dbo.Account;
import com.cbd.backend.model.NewAccount;
import com.cbd.backend.model.dbo.User;
import com.cbd.backend.service.AccountService;
import com.cbd.backend.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountRestController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;


    static Logger log = Logger.getLogger( AccountRestController.class.getName()) ;

    @RequestMapping(value= "${api.createAccount}", method = RequestMethod.POST)
    public ResponseEntity<?> createAccount(@RequestBody NewAccount newAccount ) {
        log.info( "received request " + newAccount);
        AccountValidation av = accountService.validateAccount( newAccount );

        if ( av.isValid() ) {
            log.error( "Invalid Account Creation Request: " + Helpers.objectToJson( newAccount ) );
            log.error( "Validation Result: " + Helpers.objectToJson( av ) );
            return ResponseEntity.ok( av );
        }



        Account savedAccount;
        try {
            savedAccount = accountService.createAccount( newAccount );
        } catch ( Exception e ) {
            log.error( "Failed to create account" );
            return ResponseEntity.ok( "Account Creation Exception" );
        }
        User savedUser;
        try {
            savedUser = userService.addUser( newAccount.getNewUser() );
        } catch ( Exception e ) {
            log.error( "Failed to create user account", e );
            return ResponseEntity.ok( savedAccount );
        }
        NewAccount result = new NewAccount( savedAccount, savedUser );

        return ResponseEntity.ok( result );
    }
}
