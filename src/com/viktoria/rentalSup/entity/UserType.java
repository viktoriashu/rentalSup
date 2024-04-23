package com.viktoria.rentalSup.entity;

import lombok.*;


@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor


public class UserType {
    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String number;
    private Role role;
}
