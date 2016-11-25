package com.cbd.backend.service;

import com.cbd.backend.model.Account;

/**
 * Created by warrenvoelkl on 2016-11-24.
 */
public interface AccountService {
    String createAccount( Account account );
    String prevalidateAccount( Account account );
    String deactiveAccount( String accountId );
    String activeAccount( String accountId );
}
