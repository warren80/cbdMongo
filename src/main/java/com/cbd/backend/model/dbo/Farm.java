package com.cbd.backend.model.Account.dbo;

import com.cbd.backend.model.Measurements;
import com.cbd.backend.model.dbo.FarmPlotScheme;
import com.cbd.backend.model.Model;
import org.springframework.data.annotation.Id;

public class Farm extends Model {
    private String farmName;
    private String farmPhone;
    private String secondaryPhone;
    private Measurements measurements;
    private FarmPlotScheme farmPlotScheme;
    private long subscriptionEndDate;
    private boolean enabled;
    private BillingDetails billingDetails;
    private Address address;
    private LanguageAndCountry languageAndCountry;
    @Id
    private String id;

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public String getFarmPhone() {
        return farmPhone;
    }

    public void setFarmPhone(String farmPhone) {
        this.farmPhone = farmPhone;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecondaryAccountPhone() {
        return secondaryPhone;
    }

    public void setSecondaryAccountPhone(String secondaryAccountPhone) {
        this.secondaryPhone = secondaryAccountPhone;
    }


    public LanguageAndCountry getLanguageAndCountry() {
        return languageAndCountry;
    }

    public void setLanguageAndCountry(LanguageAndCountry languageAndCountry) {
        this.languageAndCountry = languageAndCountry;
    }
}
