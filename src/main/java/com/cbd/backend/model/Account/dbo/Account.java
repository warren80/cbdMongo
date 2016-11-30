package com.cbd.backend.model.Account.dbo;

import com.cbd.backend.model.Measurements;
import com.cbd.backend.model.dbo.FarmPlotScheme;
import com.cbd.backend.model.Model;

import java.util.Locale;

public class Account extends Model {
    private String accountName;
    private String accountPhone;
    private Locale locale;
    private Measurements measurements;
    private FarmPlotScheme farmPlotScheme;
    private long subscriptionEndDate;
    private String id;
    private boolean enabled;
    private BillingDetails billingDetails;
    private Address address;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountPhone() {
        return accountPhone;
    }

    public void setAccountPhone(String accountPhone) {
        this.accountPhone = accountPhone;
    }

    public Measurements getMeasurements() {
        return measurements;
    }

    public void setMeasurements(Measurements measurements) {
        this.measurements = measurements;
    }

    public FarmPlotScheme getFarmPlotScheme() {
        return farmPlotScheme;
    }

    public void setFarmPlotScheme(FarmPlotScheme farmPlotScheme) {
        this.farmPlotScheme = farmPlotScheme;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getSubscriptionEndDate() {
        return subscriptionEndDate;
    }

    public void setSubscriptionEndDate(long subscriptionEndDate) {
        this.subscriptionEndDate = subscriptionEndDate;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public BillingDetails getBillingDetails() {
        return billingDetails;
    }

    public void setBillingDetails(BillingDetails billingDetails) {
        this.billingDetails = billingDetails;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale local) {
        this.locale = local;
    }
}
