package com.flea.flea.enumeration;

public enum PoolStatus {
    RECRUITING("Recruiting"),
    ACTIVE("Active"),
    COMPLETED("Completed");

    private final String status;
    PoolStatus(String status) {
        this.status = status;
    }
}
