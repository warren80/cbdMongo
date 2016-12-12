package com.cbd.backend.common.model;

public class OrganizationValidation {
    private boolean validOrganizationName;
    private boolean validOrganizationPhone;

    private boolean isValidSecondaryPhoneNumber;
    private boolean validLocale;
    private boolean validMeasurements;
    private boolean validOrganizationPlotScheme;
    private boolean validAddress;
    private boolean validSubscription;

    public boolean isValidOrganizationPhone() {
        return validOrganizationPhone;
    }

    public void setValidOrganizationPhone(boolean validOrganizationPhone) {
        this.validOrganizationPhone = validOrganizationPhone;
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

    public boolean isValidOrganizationPlotScheme() {
        return validOrganizationPlotScheme;
    }

    public void setValidOrganizationPlotScheme(boolean validOrganizationPlotScheme) {
        this.validOrganizationPlotScheme = validOrganizationPlotScheme;
    }

    public boolean isValidAddress() {
        return validAddress;
    }

    public void setValidAddress(boolean validAddress) {
        this.validAddress = validAddress;
    }


    public boolean isValidOrganizationName() {
        return validOrganizationName;
    }

    public void setValidOrganizationName(boolean validOrganizationName) {
        this.validOrganizationName = validOrganizationName;
    }

    public boolean isValidSubscription() {
        return validSubscription;
    }

    public void setValidSubscription(boolean validSubscription) {
        this.validSubscription = validSubscription;
    }



    public boolean isValidSecondaryPhoneNumber() {
        return isValidSecondaryPhoneNumber;
    }

    public void setValidSecondaryPhoneNumber(boolean validSecondaryPhoneNumber) {
        isValidSecondaryPhoneNumber = validSecondaryPhoneNumber;
    }

    public boolean isValid() {
        return isValidOrganizationPhone()
                && isValidSecondaryPhoneNumber()
                && isValidOrganizationName()
                && isValidOrganizationPlotScheme()
                && isValidAddress()
//                && isValidBillingDetails()
                && isValidLocale()
                && isValidMeasurements()
                && isValidSubscription();
    }
}
