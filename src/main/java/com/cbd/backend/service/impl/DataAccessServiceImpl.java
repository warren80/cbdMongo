package com.cbd.backend.service.impl;

import com.cbd.backend.database.AccountRepository;
import com.cbd.backend.database.UserRepository;
import com.cbd.backend.model.Account.dbo.Account;
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
    AccountRepository accountRepository;
    static Logger log = Logger.getLogger( DataAccessServiceImpl.class.getName() );

    @Override
    public Account saveAccount( Account account ) {
        Account a;
        try {
            a = accountRepository.save(account);
        } catch ( Exception e ) {
            log.error( "Failed to save account to database", e);
            return null;
        }
        return a;
    }

    @Override
    public Account disableAccount(final String accountName) {
        Account a;
        try {
            a = accountRepository.findByAccountNameOrderByLastUpdatedDesc( accountName );
        } catch( Exception e) {
            log.error( "Failed to retrieve account from database", e );
            return null;
        }
        a.setEnabled( false );
        try {
            return accountRepository.save( a );
        } catch( Exception e) {
            log.error( "Failed disable account on database", e );
            return a;
        }
    }

    @Override
    public boolean accountExists( String name ) {
        Account account = GetLatestAccountByName( name );
        return account != null;

    }

    @Override
    public boolean userExists( String name ) {
        User user = GetLatestUserByName( name );
        return user != null;

    }

    private User GetLatestUserByName(String name) {
        return getLatestUserByName( name );
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
        if ( u == null ) {
            return true;
        }
        return false;
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

    private Account GetLatestAccountByName(String accountName ) {
        return accountRepository.findByAccountNameOrderByLastUpdatedDesc( accountName );
    }

    private User getLatestUserByName(String userName ) {
        return userRepository.findTopByUsernameOrderByLastUpdatedDesc( userName );
    }
}
