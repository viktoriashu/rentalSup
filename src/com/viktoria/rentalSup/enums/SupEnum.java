package com.viktoria.rentalSup.enums;

public enum SupEnum {

    SUP_ID("id"),
    MODEL("model");

    private String value;

    SupEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
