package com.cbd.backend.model;

import com.cbd.backend.model.dbo.User;

/**
 * Created by warrenvoelkl on 2016-11-25.
 */
public class NewUser extends User {
    private String passwordCheck;

    public String getPasswordCheck() {
        return passwordCheck;
    }

    public void setPasswordCheck(String passwordCheck) {
        this.passwordCheck = passwordCheck;
    }
}
