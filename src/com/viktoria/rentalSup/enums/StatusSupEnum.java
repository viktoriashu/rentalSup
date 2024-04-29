package com.viktoria.rentalSup.enums;

public enum StatusSupEnum {

    STATUS_SUP_ID("id"),
    STATUS_SUP("status");

    private String value;

    StatusSupEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
