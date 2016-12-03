package com.cbd.backend.common.model;

public class FarmValidation extends UserValidation {
    private boolean validFarmName;
    private boolean validFarmPhone;

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
    private boolean validAddress;
    private boolean validSubscription;

    public boolean isValidFarmPhone() {
        return validFarmPhone;
    }

    public void setValidFarmPhone(boolean validFarmPhone) {
        this.validFarmPhone = validFarmPhone;
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

    public boolean isValidAddress() {
        return validAddress;
    }

    public void setValidAddress(boolean validAddress) {
        this.validAddress = validAddress;
    }


    public boolean isValidFarmName() {
        return validFarmName;
    }

    public void setValidFarmName(boolean validFarmName) {
        this.validFarmName = validFarmName;
    }

    public boolean isValidSubscription() {
        return validSubscription;
    }

    public void setValidSubscription(boolean validSubscription) {
        this.validSubscription = validSubscription;
    }

    @Override
    public boolean isValid() {
        return isFarmValid()
                && isEmailValid()
                && isPasswordValid()
                && isUsernameValid()
                && isFirstNameValid()
                && isLastNameValid()
                && isValidFarmPhone()
                && isValidSecondaryPhoneNumber()
                && isValidFarmName()
                && isValidFarmPlotScheme()
                && isValidAddress()
//                && isValidBillingDetails()
                && isValidLocale()
                && isValidMeasurements()
                && isValidSubscription();
    }
}
