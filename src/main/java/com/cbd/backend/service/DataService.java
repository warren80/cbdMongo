package com.cbd.backend.service;

import com.cbd.backend.model.dbo.Farm;
import com.cbd.backend.model.dbo.User;

public interface DataService {
    Farm saveFarm(Farm farm );
    Farm disableFarm(final String farmName);
    boolean userExists(String name );
    User disableUser( String username );
    User saveUser(User user);

    boolean verifyUsername(String username);

    boolean farmExists( String userName );


}
