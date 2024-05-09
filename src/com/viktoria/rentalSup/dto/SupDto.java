package com.viktoria.rentalSup.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SupDto {
    Long id;
    String description;
}
