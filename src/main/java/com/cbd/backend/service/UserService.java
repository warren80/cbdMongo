package com.cbd.backend.service;

import com.cbd.backend.common.model.UserValidation;
import com.cbd.backend.model.NewUser;
import com.cbd.backend.model.dbo.Authority;
import com.cbd.backend.model.dbo.User;

import java.util.List;

public interface UserService {
    User addUser ( final User user );
    User updateUser ( final User user );
    User updateUserAuthorities ( final List<Authority> authorities );
    UserValidation validateUser(final NewUser user );
    User disableUser( final String username );
}

