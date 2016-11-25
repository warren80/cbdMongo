package com.cbd.backend.service.impl;

import com.cbd.backend.database.AccountRepository;
import com.cbd.backend.database.UserRepository;
import com.cbd.backend.model.dbo.Account;
import com.cbd.backend.model.dbo.User;
import com.cbd.backend.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataAccessServiceImpl implements DataService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public User saveUser(User user) {
        return null;
    }

    @Override
    public boolean verifyAccount( String name ) {
        Account account = accountRepository.findByNameOrderByLastUpdatedAsc( name );
        return account != null;
    }
}
