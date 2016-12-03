package com.cbd.backend.service;

import com.cbd.backend.common.model.AccountValidation;
import com.cbd.backend.common.model.UserValidation;
import com.cbd.backend.model.Account.dbo.Account;
import com.cbd.backend.model.NewAccount;

public interface AccountService {
    Account createAccount( final Account account );
    String deactivateAccount( final String accountId );
    String activateAccount( final String accountId );

    AccountValidation validateAccount(final NewAccount account );


}
