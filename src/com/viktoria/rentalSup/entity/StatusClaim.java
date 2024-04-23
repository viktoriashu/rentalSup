package com.viktoria.rentalSup.entity;

import lombok.*;


@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor


public class StatusClaim {
    private int id;
    private String status;
}

