package com.viktoria.rentalSup.enums;

public enum PageEnum {
    REGISTRATION_PAGE("registration"),
    LOGIN_PAGE("login"),
    SUP_PAGE("sup"),
    LOCALE("locale"),
    SLASH("/");

    private String value;

    PageEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
