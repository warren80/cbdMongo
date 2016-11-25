package com.cbd.backend.service;

import com.cbd.backend.model.dbo.User;

/**
 * Created by warrenvoelkl on 2016-11-25.
 */
public interface DataService {
    User saveUser(User user);

    boolean verifyAccount( String name );
}
