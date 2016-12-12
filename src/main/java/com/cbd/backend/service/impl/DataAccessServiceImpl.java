package com.cbd.backend.service.impl;

import com.cbd.backend.database.OrganizationRepository;
import com.cbd.backend.database.UserRepository;
import com.cbd.backend.model.dbo.Organization;
import com.cbd.backend.model.dbo.User;
import com.cbd.backend.service.DataService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataAccessServiceImpl implements DataService {

    @Value( "${isPROD}" )
    boolean isProd;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrganizationRepository organizationRepository;
    static Logger log = Logger.getLogger( DataAccessServiceImpl.class.getName() );

    @Override
    public Organization saveOrganization(Organization organization) {
        Organization a;
        try {
            a = organizationRepository.save(organization);
        } catch (Exception e) {
            log.error("Failed to save organization to database", e);
            return null;
        }
        return a;
    }

    @Override
    public Organization disableOrganization(final String organizationName) {
        Organization a;
        try {
            a = organizationRepository.findByOrganizationNameOrderByLastUpdatedDesc( organizationName );
        } catch( Exception e) {
            log.error( "Failed to retrieve organization from database", e );
            return null;
        }
        a.setEnabled( false );
        try {
            return organizationRepository.save( a );
        } catch( Exception e) {
            log.error( "Failed disable organization on database", e );
            return a;
        }
    }

    @Override
    public boolean organizationExists(String name ) {
        Organization organization = organizationRepository.findFirstByOrganizationName( name );
        return organization != null;

    }

    @Override
    public boolean deleteOrganization(String organizationName) {
        if ( isProd ) {
            return false;
        }
        List<Organization> organizations = organizationRepository.findAllByOrganizationName( organizationName );
        organizationRepository.delete(organizations);
        return true;
    }

    public boolean deleteUser( String user ) {
        if ( isProd ) {
            return false;
        }
        List<User> users = userRepository.findAllByUsername( user );
        userRepository.delete(users);
        return true;
    }

    @Override
    public User getUser(String username) {
        return userRepository.findTopByUsernameOrderByLastUpdatedDesc( username );
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

    private Organization getLatestOrganizationByName(String organizationName ) {
        return organizationRepository.findByOrganizationNameOrderByLastUpdatedDesc( organizationName );
    }

    private User getLatestUserByName(String userName ) {
        return userRepository.findTopByUsernameOrderByLastUpdatedDesc( userName );
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setOrganizationRepository(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }
}
