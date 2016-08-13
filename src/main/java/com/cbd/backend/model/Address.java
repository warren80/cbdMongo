package com.cbd.backend.model;

import org.springframework.data.annotation.Id;

public class Address {
    private String city;
    private String streetNumber;
    private String postalCode;
    private String province;
    private String country;
    @Id
    private String id;

    public Address(String city, String streetNumber, String postalCode, String province, String country, String id) {
        this.city = city;
        this.postalCode = postalCode;
        this.province = province;
        this.streetNumber = streetNumber;
        this.country = country;
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
