package com.flea.flea.enumeration;

public enum RequestType {

    TOPUP("Topup"),
    WITHDRAW("Withdraw");

    private final String type;
    RequestType(String type) {
        this.type = type;
    }

}
