package com.viktoria.rentalSup.dto.UserTypeDto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateUserTypeDto {
    String firstName;
    String lastName;
    String login;
    String password;
    String number;
    String roleId;
}
