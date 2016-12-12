package com.cbd.backend.service.impl;

import com.cbd.backend.common.Helpers;
import com.cbd.backend.common.ValidationHelper;
import com.cbd.backend.model.UserWithPasswordCheck;
import com.cbd.backend.model.dbo.Authority;
import com.cbd.backend.model.dbo.User;
import com.cbd.backend.common.model.UserValidation;
import com.cbd.backend.service.DataService;
import com.cbd.backend.service.UserService;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.cbd.backend.common.Helpers.objectToJson;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private DataService dataAccessService;

    static Logger log = Logger.getLogger( UserServiceImpl.class.getName() );

    @Override
    public User addUser( final User user ) {

        user.setEnabled( true );
        user.setPassword(Helpers.passwordEncoder( user.getPassword() ) );
        log.debug( objectToJson( user ) );
        User savedUser = persistUser( user );

        return savedUser;
    }

    @Override
    public User updateUser(final User user) {
        return null;
    }

    @Override
    public User updateUserAuthorities(List<Authority> authorities) {
        return null;
    }

    @Override
    public UserValidation validateUser( final UserWithPasswordCheck user ) {
        UserValidation userValidation = new UserValidation();
        new ValidationHelper().validateUserFields( userValidation, user );
        userValidation.setOrganizationValid( dataAccessService.userExists( user.getOrganization() ) );
        userValidation.setUsernameValid( dataAccessService.verifyUsername( user.getUsername() ) );

        return userValidation;
    }



    @Override
    public User disableUser( final String username) {
        try {
            return dataAccessService.disableUser(username);
        } catch ( Exception e ) {
            log.error( "Failed to disable user", e );
        }
        return null;
    }

    @Override
    public void update(String username, User user) {
//        dataAccessService.GetLatestUserByName( username );
    }

    @Override
    public boolean deleteUser( String user ) {
        return dataAccessService.deleteUser( user );

    }

    @Override
    public User setOrganization(String username, String organizationName) {
        User user = null;
        try {
            user = dataAccessService.getUser( username );
        } catch ( Exception e ) {
            log.error( "Unable to retrieve user", e );
            return null;
        }
        user.setOrganization( organizationName );
        try {
            dataAccessService.saveUser(user);
        } catch ( Exception e ) {
            log.error( "Unable to save use to db organization not updated", e );
        }
        return user;

    }


    private User persistUser( final User user ) {
        User savedUser;
        try {
            log.info( "Saving New User: " + user.getUsername() );
            savedUser = dataAccessService.saveUser(user);
        } catch ( Exception e ) {
            log.error ( "Database Exception: ", e );
            throw new MongoException( "Failed to save user: " + user.getUsername() );
        }
        return savedUser;
    }

    /**
     * facilitate testing
     * @param dataAccessService
     */
    public void setDataAccessService(DataService dataAccessService) {
        this.dataAccessService = dataAccessService;
    }
}
