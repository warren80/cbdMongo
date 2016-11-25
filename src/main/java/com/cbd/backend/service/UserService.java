package com.cbd.backend.service;

import com.cbd.backend.model.Authority;
import com.cbd.backend.model.User;

import java.util.List;

public interface UserService {
    User addUser (User user );
    User updateUser ( User user );
    User updateUserAuthorities ( List<Authority> authorities );
    boolean prevalidateUser( User user );
}

