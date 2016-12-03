package com.cbd.backend.model.dbo;

import java.util.TimeZone;

public class LanguageAndCountry {
    private String language;
    private String country;
    private String variant;

    public LanguageAndCountry(String language, String country, String variant ) {
        this.language = language;
        this.country = country;
        this.variant = variant;
    }

    public LanguageAndCountry() {}

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }
}
