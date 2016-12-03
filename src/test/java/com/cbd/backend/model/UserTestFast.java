package com.cbd.backend.model;

import com.cbd.backend.database.UserRepository;
import com.cbd.backend.model.dbo.Authority;
import com.cbd.backend.model.dbo.User;
import com.google.gson.Gson;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.cbd.backend.TestFactories.*;
import static com.cbd.backend.model.AuthorityPermission.ROLE_ACCOUNT_ADMIN;
import static com.cbd.backend.model.AuthorityPermission.ROLE_SITE_USER;
import static org.junit.Assert.*;

public class UserTestFast {
    Gson gson = new Gson();

    @Test
    public void createUserFast() {
        User u = getStockUser();

        assertNotNull( u.getAuthority() );
        assertTrue( u.getUsername().equals( username ) );
        assertTrue( u.getPassword().equals( password ) );
        assertTrue( u.getAccount().equals( account ) );
        assertTrue( u.getFirstName().equals( firstName ) );
        assertTrue( u.getLastName().equals( lastName ) );
        assertTrue( u.getEmail().equals( email ) );
    }




}
