package com.cbd.backend.service.impl;

import com.cbd.backend.common.ValidationHelper;
import com.cbd.backend.common.model.AccountValidation;
import com.cbd.backend.model.Account.dbo.Account;
import com.cbd.backend.model.NewAccount;
import com.cbd.backend.service.AccountService;
import com.cbd.backend.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {


    //TODO subscription handling
    @Autowired
    DataService dataAccessService;

    @Override
    public Account createAccount(Account account) {
        return dataAccessService.saveAccount( account );
    }

    @Override
    public String deactivateAccount(String accountId) {
        return null;
    }

    @Override
    public String activateAccount(String accountId) {
        return null;
    }

    @Override
    public AccountValidation validateAccount(final NewAccount account ) {
        AccountValidation accountValidation = new AccountValidation();
        new ValidationHelper().validateNewAccount( accountValidation, account );
        //TODO database checks
        accountValidation.setAccountValid( dataAccessService.accountExists( account.getAccountName() ) );
        accountValidation.setUsernameValid( dataAccessService.verifyUsername( account.getNewUser().getUsername() ) );

        return accountValidation;
    }
}
