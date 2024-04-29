package com.viktoria.rentalSup.enums;

public enum RoleEnum {

    ROLE_ID("id"),
    ROLE_NAME("role_name");

    private String value;

    RoleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
