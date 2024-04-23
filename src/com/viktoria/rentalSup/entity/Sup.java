package com.viktoria.rentalSup.entity;

import lombok.*;


@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor


public class Sup {
    private Long id;
    private String model;
    private StatusSup statusSup;
}
