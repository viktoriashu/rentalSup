package com.viktoria.rentalSup.enums;

public enum UserTypeEnum {

    USER_TYPE_ID("id"),
    FIRST_NAME("first_name"),
    LAST_NAME("last_name"),
    LOGIN("login"),
    PASSWORD("password"),
    NUMBER("number");

    private String value;

    UserTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
