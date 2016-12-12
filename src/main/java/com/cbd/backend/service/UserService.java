package com.cbd.backend.service;

import com.cbd.backend.common.model.UserValidation;
import com.cbd.backend.model.UserWithPasswordCheck;
import com.cbd.backend.model.dbo.Authority;
import com.cbd.backend.model.dbo.User;
import com.mongodb.WriteResult;

import java.util.List;

public interface UserService {
    User addUser ( final User user );
    User updateUser ( final User user );
    User updateUserAuthorities ( final List<Authority> authorities );
    UserValidation validateUser( final UserWithPasswordCheck user );
    User disableUser( final String username );

    void update( String username, User user );

    boolean deleteUser( String farm );

    User setOrganization(String username, String organizationName );
}


