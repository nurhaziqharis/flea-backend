package com.flea.flea.enumeration;

public enum MoneyRequestStatus {

    PROCESSING("Processing"),
    HALF_PAID("Half Paid"),
    SUCCESS("Success"),
    FAILED("Failed");

    private final String status;
    MoneyRequestStatus(String status) {
        this.status = status;
    }

}
