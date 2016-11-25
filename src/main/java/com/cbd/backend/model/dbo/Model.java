package com.cbd.backend.model.dbo;

abstract class Model {
    long lastUpdated;
    Model() {
        lastUpdated = System.currentTimeMillis();
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
