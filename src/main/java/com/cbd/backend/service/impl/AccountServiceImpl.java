package com.cbd.backend.service.impl;

import com.cbd.backend.model.dbo.Account;
import com.cbd.backend.service.AccountService;
import com.cbd.backend.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by warrenvoelkl on 2016-11-24.
 */
public class AccountServiceImpl implements AccountService {

    @Autowired
    DataService dataService;

    @Override
    public String createAccount(Account account) {
        return null;
    }

    @Override
    public String deactivateAccount(String accountId) {
        return null;
    }

    @Override
    public String activateAccount(String accountId) {
        return null;
    }
}
