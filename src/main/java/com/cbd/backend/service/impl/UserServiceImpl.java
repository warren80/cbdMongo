package com.cbd.backend.service.impl;

import com.cbd.backend.database.UserRepository;
import com.cbd.backend.helper.EmailValidator;
import com.cbd.backend.helper.Helpers;
import com.cbd.backend.helper.PasswordValidator;
import com.cbd.backend.model.NewUser;
import com.cbd.backend.model.dbo.Authority;
import com.cbd.backend.model.dbo.User;
import com.cbd.backend.model.UserValidationResult;
import com.cbd.backend.service.DataService;
import com.cbd.backend.service.UserService;
import com.mongodb.MongoException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.cbd.backend.helper.Helpers.objectToJson;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    DataService dataAccessService;

    static Logger log = Logger.getLogger( UserServiceImpl.class.getName() );

    @Override
    public User addUser(User user) {

        user.setPassword(Helpers.passwordEncoder( user.getPassword() ) );
        log.info( "Saving New User: " + user.getUsername() );
        log.debug( objectToJson( user ) );
        User savedUser = persistUser( user );

        return savedUser;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public User updateUserAuthorities(List<Authority> authorities) {
        return null;
    }

    @Override
    public UserValidationResult validateUser (NewUser user ) {
        String email = user.getEmail();
        UserValidationResult userValidationResult = new UserValidationResult();

        PasswordValidator pwv = new PasswordValidator( userValidationResult );


        log.debug( "Validating email: " + email);
        userValidationResult.setEmailValid( EmailValidator.isValidMail( email ) );
        pwv.validateNewPass( user.getPassword(), user.getPasswordCheck() );
        userValidationResult.setAccountValid( dataAccessService.verifyAccount( user.getAccount() ) );

        return userValidationResult;
    }

    private User persistUser( User user ) {
        User savedUser = null;
        try {
            savedUser = dataAccessService.saveUser(user);
        } catch ( Exception e ) {
            log.error ( "Database Exception: ", e );
            throw new MongoException( "Failed to save user: " + user.getUsername() );
        }
        return savedUser;
    }
}
