package com.cbd.backend.model;

import java.io.Serializable;

/**
 * Created by warrenvoelkl on 2016-11-25.
 */
public class UserValidationResult implements Serializable {

    private boolean isEmailValid;
    private boolean isUsernameValid;
    private boolean isPasswordValid;
    private boolean isAccountValid;
    private boolean isFirstNameValid;
    private boolean isLastNameValid;

    public UserValidationResult() { }

    public boolean isEmailValid() {
        return isEmailValid;
    }

    public void setEmailValid(boolean emailValid) {
        isEmailValid = emailValid;
    }

    public boolean isUsernameValid() {
        return isUsernameValid;
    }

    public void setUsernameValid(boolean usernameValid) {
        isUsernameValid = usernameValid;
    }

    public boolean isPasswordValid() {
        return isPasswordValid;
    }

    public void setPasswordValid(boolean passwordValid) {
        isPasswordValid = passwordValid;
    }



    public boolean isLastNameValid() {
        return isLastNameValid;
    }

    public void setLastNameValid(boolean lastNameValid) {
        isLastNameValid = lastNameValid;
    }



    public boolean isAccountValid() {
        return isAccountValid;
    }

    public void setAccountValid(boolean accountValid) {
        isAccountValid = accountValid;
    }

    public boolean isFirstNameValid() {
        return isFirstNameValid;
    }

    public void setFirstNameValid(boolean firstNameValid) {
        isFirstNameValid = firstNameValid;
    }
}
