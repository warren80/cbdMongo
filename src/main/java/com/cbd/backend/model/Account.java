package com.cbd.backend.model;

public class Account {
    private String accountName;
    private String accountPhone;
    private String timeZone;
    private Measurements measurements;
    private FarmPlotScheme farmPlotScheme;
    private String id;

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

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
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
}
