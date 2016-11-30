package com.cbd.backend.model;

import com.cbd.backend.model.dbo.User;


public class NewUser extends User {
    private String passwordCheck;

    public NewUser(User user) {
        setUsername( user.getUsername() );
        setPassword( user.getPassword() );
        setFirstName( user.getFirstName() );
        setLastName( user.getLastName() );
        setEmail( user.getEmail() );
        setAccount( user.getAccount() );
        setId( user.getId() );
    }

    public NewUser() {}



    public String getPasswordCheck() {
        return passwordCheck;
    }

    public void setPasswordCheck(String passwordCheck) {
        this.passwordCheck = passwordCheck;
    }
}
