package com.viktoria.rentalSup.entity;

import lombok.*;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor



public class Role {
    private int id;
    private String roleName;
}
