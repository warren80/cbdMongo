package com.cbd.backend.service;

import com.cbd.backend.model.NewUser;
import com.cbd.backend.model.dbo.Authority;
import com.cbd.backend.model.dbo.User;
import com.cbd.backend.model.UserValidationResult;

import java.util.List;

public interface UserService {
    User addUser (User user );
    User updateUser ( User user );
    User updateUserAuthorities ( List<Authority> authorities );

    UserValidationResult validateUser( NewUser user);
}

