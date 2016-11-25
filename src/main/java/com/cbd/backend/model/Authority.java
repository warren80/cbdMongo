package com.cbd.backend.model;

public class Authority {
    private boolean authorized;
    public String name;

    public Authority() {}

    public Authority( String name, boolean authorized ) {
        this.setAuthorized( authorized );
        this.name = name;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
