package com.viktoria.rentalSup.entity;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor


public class Claim {
    private Long id;
    private UserType client;
    private UserType admin;
    private Sup sup;
    private StatusClaim statusClaim;
    private LocalDate dataStartRent;
    private int durationRent;
    private BigDecimal price;
}
