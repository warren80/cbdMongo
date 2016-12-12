package com.cbd.backend.common.model;

import java.io.Serializable;

public class UserValidation implements Serializable, Validation {

    private boolean isEmailValid;
    private boolean isUsernameValid;
    private boolean isPasswordValid;
    private boolean isOrganizationValid;
    private boolean isFirstNameValid;
    private boolean isLastNameValid;

    public UserValidation() { }

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



    public boolean isOrganizationValid() {
        return isOrganizationValid;
    }

    public void setOrganizationValid(boolean organizationValid) {
        isOrganizationValid = organizationValid;
    }

    public boolean isFirstNameValid() {
        return isFirstNameValid;
    }

    public void setFirstNameValid(boolean firstNameValid) {
        isFirstNameValid = firstNameValid;
    }

    public boolean isValid() {
        return isOrganizationValid()
                && isEmailValid()
                && isPasswordValid()
                && isUsernameValid()
                && isFirstNameValid()
                && isLastNameValid();
    }
}
