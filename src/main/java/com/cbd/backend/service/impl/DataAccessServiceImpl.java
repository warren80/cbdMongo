package com.cbd.backend.service.impl;

import com.cbd.backend.database.FarmRepository;
import com.cbd.backend.database.UserRepository;
import com.cbd.backend.model.dbo.Farm;
import com.cbd.backend.model.dbo.User;
import com.cbd.backend.service.DataService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataAccessServiceImpl implements DataService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FarmRepository farmRepository;
    static Logger log = Logger.getLogger( DataAccessServiceImpl.class.getName() );

    @Override
    public Farm saveFarm(Farm farm) {
        Farm a;
        try {
            a = farmRepository.save(farm);
        } catch (Exception e) {
            log.error("Failed to save farm to database", e);
            return null;
        }
        return a;
    }

    @Override
    public Farm disableFarm(final String farmName) {
        Farm a;
        try {
            a = farmRepository.findByFarmNameOrderByLastUpdatedDesc( farmName );
        } catch( Exception e) {
            log.error( "Failed to retrieve farm from database", e );
            return null;
        }
        a.setEnabled( false );
        try {
            return farmRepository.save( a );
        } catch( Exception e) {
            log.error( "Failed disable farm on database", e );
            return a;
        }
    }

    @Override
    public boolean farmExists(String name ) {
        Farm farm = farmRepository.findFirstByFarmName( name );
        return farm != null;

    }

    @Override
    public boolean userExists( String name ) {
        User user = getLatestUserByName( name );
        return user != null;

    }

    @Override
    public User saveUser( final User u ) {
        User savedUser;
        try {
            savedUser = userRepository.save( u );
        } catch ( Exception e ) {
            log.error( "Failed to save user to database", e);
            return null;
        }
        return savedUser;
    }

    @Override
    public boolean verifyUsername(String username) {
        User u = userRepository.findFirstByUsername(username);
        return u != null;
    }


    @Override
    public User disableUser(String username) {
        User u;
        try {
            u = userRepository.findTopByUsernameOrderByLastUpdatedDesc(username);
        } catch( Exception e) {
            log.error( "Failed to retrieve user from database", e );
            return null;
        }
        u.setEnabled( false );
        try {
            return saveUser( u );
        } catch( Exception e) {
            log.error( "Failed to update user in database" );
            return u;
        }
    }

    private Farm getLatestFarmByName(String farmName ) {
        return farmRepository.findByFarmNameOrderByLastUpdatedDesc( farmName );
    }

    private User getLatestUserByName(String userName ) {
        return userRepository.findTopByUsernameOrderByLastUpdatedDesc( userName );
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setFarmRepository(FarmRepository farmRepository) {
        this.farmRepository = farmRepository;
    }
}
