package com.cbd.backend.model;

public abstract class Model {
    public long lastUpdated;
    public Model() {
        lastUpdated = System.currentTimeMillis();
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated( final long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
