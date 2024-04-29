package com.viktoria.rentalSup.enums;

public enum ClaimEnum {
    CLAIM_ID("id"),
    ID_CLIENT("id_client"),
    ID_ADMIN("id_admin"),
    ID_SUP("id_sup"),
    ID_STATUS_CLAIM("id_status_claim"),
    DATA_START_RENT("data_start_rent"),
    DURATION_RENT("duration_rent"),
    PRICE("price");

    private String value;

    ClaimEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
