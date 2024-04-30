package com.viktoria.rentalSup.enums;

public enum PageEnum {
    REGISTRATION_PAGE("registration");

    private String value;

    PageEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
