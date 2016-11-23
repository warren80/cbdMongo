package com.cbd.backend.model;


import java.util.List;

public class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String account;
    private long passwordUpdateDate;
    private long lastUpdated;
    private long id;
    private List<Authority> authority;
    private boolean isEnabled;

    public User(String username, String password, String firstName, String lastName,  String email, String account, long passwordUpdateDate, long id, List<Authority> authority, boolean isEnabled){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.account = account;
        this.passwordUpdateDate = passwordUpdateDate;
        this.id = id;
        this.authority = authority;
        this.isEnabled = isEnabled;
    }

    public User(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

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

    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Authority> getAuthority() {
        return authority;
    }

    public void setAuthory(List<Authority> authority) {
        this.authority = authority;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
