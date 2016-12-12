package com.cbd.backend.model.dbo;

import com.cbd.backend.model.Measurements;
import com.cbd.backend.model.Model;
import org.springframework.data.annotation.Id;

public class Organization extends Model {
    private String organizationName;
    private String organizationPhone;
    private String secondaryPhone;
    private Measurements measurements;
    private FarmPlotScheme farmPlotScheme;
    private long subscriptionEndDate;
    private boolean enabled;
//    private BillingDetails billingDetails;
    private Address address;
    private LanguageAndCountry languageAndCountry;
    @Id
    private String _id;

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationPhone() {
        return organizationPhone;
    }

    public void setOrganizationPhone(String organizationPhone) {
        this.organizationPhone = organizationPhone;
    }

    public Measurements getMeasurements() {
        return measurements;
    }

    public void setMeasurements(Measurements measurements) {
        this.measurements = measurements;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getSecondaryOrganizationPhone() {
        return secondaryPhone;
    }

    public void setSecondaryOrganizationPhone(String secondaryOrganizationPhone) {
        this.secondaryPhone = secondaryOrganizationPhone;
    }


    public LanguageAndCountry getLanguageAndCountry() {
        return languageAndCountry;
    }

    public void setLanguageAndCountry(LanguageAndCountry languageAndCountry) {
        this.languageAndCountry = languageAndCountry;
    }

    public FarmPlotScheme getFarmPlotScheme() {
        return farmPlotScheme;
    }

    public void setFarmPlotScheme(FarmPlotScheme farmPlotScheme) {
        this.farmPlotScheme = farmPlotScheme;
    }
}
