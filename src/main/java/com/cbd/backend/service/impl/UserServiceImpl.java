package com.cbd.backend.service.impl;

import com.cbd.backend.database.UserRepository;
import com.cbd.backend.helper.Helpers;
import com.cbd.backend.model.Authority;
import com.cbd.backend.model.User;
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
    private UserRepository userRepository;

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
    public boolean prevalidateUser(User user) {
        String email = user.getEmail();
        log.debug( "Validating email: " + email);
        return false;

    }

    private User persistUser( User user ) {
        User savedUser = null;
        try {
            savedUser = userRepository.save(user);
        } catch ( Exception e ) {
            log.error ( "Database Exception: ", e );
            throw new MongoException( "Failed to save user: " + user.getUsername() );
        }
        return savedUser;
    }



}
