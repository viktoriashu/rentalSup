package com.viktoria.rentalSup.dto.UserTypeDto;

import com.viktoria.rentalSup.entity.Role;
import lombok.*;

import java.util.Objects;

@Value
@Builder

public class UserTypeDto {
    Long id;
    String firstName;
    String lastName;
    String login;
    String number;
    Role role;
}
