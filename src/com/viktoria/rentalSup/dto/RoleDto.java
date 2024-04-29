package com.viktoria.rentalSup.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RoleDto {
    String id;
    String roleName;
}
