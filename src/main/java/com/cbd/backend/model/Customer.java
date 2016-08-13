package com.cbd.backend.model;

import org.springframework.data.annotation.Id;

public class Customer {
    @Id
    private String id;
    private Address address;
    private String email;
    private String phoneNumber;
    private String workNumber;

    public Customer(Address address, String email, String phoneNumber, String workNumber, String id) {
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.workNumber = workNumber;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }
}
