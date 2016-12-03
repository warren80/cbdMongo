package com.cbd.backend.service;

import com.cbd.backend.model.Account.dbo.Account;
import com.cbd.backend.model.dbo.User;

public interface DataService {
    Account saveAccount( Account account );
    Account disableAccount(final String accountName);
    boolean userExists(String name );
    User disableUser( String username );
    User saveUser(User user);

    boolean verifyUsername(String username);

    boolean accountExists( String userName );


}
