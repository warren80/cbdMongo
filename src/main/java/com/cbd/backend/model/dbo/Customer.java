package com.cbd.backend.model.dbo;

import com.cbd.backend.model.Account.dbo.Account;
import com.cbd.backend.model.Model;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class Customer extends Model {
    private Account address;
    private String email;
    private String phoneNumber;
    private String workNumber;
    private String name;
    private LocalDateTime dateTime;
    @Id
    private String id;

    public Customer(String name, Account address, String email, String phoneNumber, String workNumber) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.workNumber = workNumber;
    }

    public Account getAddress() {
        return address;
    }

    public void setAddress(Account address) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
