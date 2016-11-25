package com.cbd.backend.model;

/**
 * Created by warrenvoelkl on 2016-11-25.
 */
public class PasswordRules {

    private int length;
    private boolean isSpecialCharacterRequired;
    private boolean isNumberRequired;
    private boolean isUppercaseRequired;
    private boolean isLowercaseRequired;

    private PasswordRules instance;

    private PasswordRules() {
        length = 8;
        isSpecialCharacterRequired = true;
        isNumberRequired = true;
        isUppercaseRequired = true;
        isLowercaseRequired = true;
    }

    synchronized PasswordRules getInstance() {
        PasswordRules pr = null;
        if ( instance == null) {
            instance = new PasswordRules();
        }
        return instance;
    }


    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isNumberRequired() {
        return isNumberRequired;
    }

    public void setNumberRequired(boolean numberRequired) {
        isNumberRequired = numberRequired;
    }

    public boolean isUppercaseRequired() {
        return isUppercaseRequired;
    }

    public void setUppercaseRequired(boolean uppercaseRequired) {
        isUppercaseRequired = uppercaseRequired;
    }

    public boolean isLowercaseRequired() {
        return isLowercaseRequired;
    }

    public void setLowercaseRequired(boolean lowercaseRequired) {
        isLowercaseRequired = lowercaseRequired;
    }

    public boolean isSpecialCharacterRequired() {
        return isSpecialCharacterRequired;
    }

    public void setSpecialCharacterRequired(boolean specialCharacterRequired) {
        isSpecialCharacterRequired = specialCharacterRequired;
    }
}
