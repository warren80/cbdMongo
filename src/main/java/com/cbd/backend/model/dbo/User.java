package com.cbd.backend.model.dbo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class User extends Model implements Serializable {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String account;
    private long passwordUpdateDate;
    private String id;

    public long getIdSecurityNumber() {
        return idSecurityNumber;
    }

    public void setIdSecurityNumber(long idSecurityNumber) {
        this.idSecurityNumber = idSecurityNumber;
    }

    private long idSecurityNumber;
    private List<Authority> authority;
    private boolean isEnabled;

    public User() {}

    //TODO remove
//    public User(String username,
//                String password,
//                String firstName,
//                String lastName,
//                String email,
//                String account,
//                long passwordUpdateDate,
//                long id,
//                List<Authority> authority,
//                boolean isEnabled){
//        this.username = username;
//        this.password = password;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.account = account;
//        this.passwordUpdateDate = passwordUpdateDate;
//        this.idSecurityNumber = id;
//        this.authority = authority;
//        this.isEnabled = isEnabled;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public long getPasswordUpdateDate() {
        return passwordUpdateDate;
    }

    public void setPasswordUpdateDate(long passwordUpdateDate) {
        this.passwordUpdateDate = passwordUpdateDate;
    }

    @JsonIgnore
    public String getId() {
        return id;
    }

    @JsonIgnore
    public void setId(String id) {
        this.id = id;
    }

    public List<Authority> getAuthority() {
        return authority;
    }

    public void setAuthority(List<Authority> authority) {
        this.authority = authority;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public void setSecurityId(long l) {
    }
}