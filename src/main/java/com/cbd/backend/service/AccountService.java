package com.cbd.backend.service;

import com.cbd.backend.model.dbo.Account;

/**
 * Created by warrenvoelkl on 2016-11-24.
 */
public interface AccountService {
    String createAccount( Account account );
    String deactivateAccount( String accountId );
    String activateAccount( String accountId );
}
