package com.flea.flea.enumaration;

public enum TransactionStatus {
    PROCESSING("Processing"),
    HALF_PAID("Half Paid"),
    SUCCESS("Success"),
    FAILED("Failed");

    private final String status;
    TransactionStatus(String status) {
        this.status = status;
    }
}
