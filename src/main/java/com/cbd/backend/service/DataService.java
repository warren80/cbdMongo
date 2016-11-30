package com.cbd.backend.service;

import com.cbd.backend.model.Account.dbo.Account;
import com.cbd.backend.model.dbo.User;

/**
 * Created by warrenvoelkl on 2016-11-25.
 */
public interface DataService {
    Account saveAccount( Account account );
    Account disableAccount(final String accountName);
    boolean accountExists(String name );
    User disableUser( String username );
    User saveUser(User user);

    boolean verifyUsername(String username);

    boolean userExists( String userName );
}
