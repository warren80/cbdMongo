package com.cbd.backend.model.dbo;

import com.cbd.backend.model.Model;
import org.springframework.data.annotation.Id;

public class Address extends Model {
    private String city;
    private String streetNumber;
    private String postalCode;
    private String province;
    private String country;
    @Id
    private String id;

    public Address( final String city,
                    final String streetNumber,
                    final String postalCode,
                    final String province,
                    final String country,
                    final String id) {
        this.city = city;
        this.postalCode = postalCode;
        this.province = province;
        this.streetNumber = streetNumber;
        this.country = country;
        this.id = id;
    }

    public Address() {}

    public String getCity() {
        return city;
    }

    public void setCity( final String city  ) {
        this.city = city;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber( final String streetNumber ) {
        this.streetNumber = streetNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode( final String postalCode ) {
        this.postalCode = postalCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince( final String province ) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry( final String country ) {
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public void setId( final String id ) {
        this.id = id;
    }
}
