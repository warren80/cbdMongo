package com.cbd.backend.model;

import com.cbd.backend.model.dbo.User;


public class UserWithPasswordCheck extends User {
    private String passwordCheck;

    public UserWithPasswordCheck(User user) {
        setUsername( user.getUsername() );
        setPassword( user.getPassword() );
        setFirstName( user.getFirstName() );
        setLastName( user.getLastName() );
        setEmail( user.getEmail() );
        setOrganization( user.getOrganization() );
        set_id( user.get_id() );
    }

    public UserWithPasswordCheck() {}



    public String getPasswordCheck() {
        return passwordCheck;
    }

    public void setPasswordCheck(String passwordCheck) {
        this.passwordCheck = passwordCheck;
    }
}
