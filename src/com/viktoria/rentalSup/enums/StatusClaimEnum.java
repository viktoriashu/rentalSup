package com.viktoria.rentalSup.enums;

public enum StatusClaimEnum {

    STATUS_CLAIM_ID("id"),
    STATUS_CLAIM("status");

    private String value;

    StatusClaimEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
