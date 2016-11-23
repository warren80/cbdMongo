package com.cbd.backend.service;

import com.cbd.backend.model.Authority;
import com.cbd.backend.model.User;
import com.sun.tools.javac.util.List;

public interface UserService {
    User addUser (String username, String password, String firstName, String lastName, String email, String account, long passwordUpdateDate, long timestamp, long id, List<Authority> authorizations );
}

