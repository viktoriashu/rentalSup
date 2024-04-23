package com.viktoria.rentalSup.dto;

import lombok.*;

import java.util.Objects;

@Value
@Builder

public class UserTypeDto {
    Long id;
    String description;
}
