package com.cbd.backend.common.model;

public class AccountValidation extends UserValidation {
    private boolean validAccountName;
    private boolean validAccountPhone;

    public boolean isValidSecondaryPhoneNumber() {
        return isValidSecondaryPhoneNumber;
    }

    public void setValidSecondaryPhoneNumber(boolean validSecondaryPhoneNumber) {
        isValidSecondaryPhoneNumber = validSecondaryPhoneNumber;
    }

    private boolean isValidSecondaryPhoneNumber;
    private boolean validLocale;
    private boolean validMeasurements;
    private boolean validFarmPlotScheme;
    private boolean validBillingDetails;
    private boolean validAddress;
    private boolean validSubscription;

    public boolean isValidAccountPhone() {
        return validAccountPhone;
    }

    public void setValidAccountPhone(boolean validAccountPhone) {
        this.validAccountPhone = validAccountPhone;
    }

    public boolean isValidLocale() {
        return validLocale;
    }

    public void setValidLocale(boolean validLocale) {
        this.validLocale = validLocale;
    }

    public boolean isValidMeasurements() {
        return validMeasurements;
    }

    public void setValidMeasurements(boolean validMeasurements) {
        this.validMeasurements = validMeasurements;
    }

    public boolean isValidFarmPlotScheme() {
        return validFarmPlotScheme;
    }

    public void setValidFarmPlotScheme(boolean validFarmPlotScheme) {
        this.validFarmPlotScheme = validFarmPlotScheme;
    }

    public boolean isValidBillingDetails() {
        return validBillingDetails;
    }

    public void setValidBillingDetails(boolean validBillingDetails) {
        this.validBillingDetails = validBillingDetails;
    }

    public boolean isValidAddress() {
        return validAddress;
    }

    public void setValidAddress(boolean validAddress) {
        this.validAddress = validAddress;
    }


    public boolean isValidAccountName() {
        return validAccountName;
    }

    public void setValidAccountName(boolean validAccountName) {
        this.validAccountName = validAccountName;
    }

    public boolean isValidSubscription() {
        return validSubscription;
    }

    public void setValidSubscription(boolean validSubscription) {
        this.validSubscription = validSubscription;
    }

    @Override
    public boolean isValid() {
        return isAccountValid()
                && isEmailValid()
                && isPasswordValid()
                && isUsernameValid()
                && isFirstNameValid()
                && isLastNameValid()
                && isValidAccountPhone()
                && isValidSecondaryPhoneNumber()
                && isValidAccountName()
                && isValidFarmPlotScheme()
                && isValidAddress()
                && isValidBillingDetails()
                && isValidLocale()
                && isValidMeasurements()
                && isValidSubscription();
    }
}
