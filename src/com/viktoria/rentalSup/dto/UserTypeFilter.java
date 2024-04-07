package com.viktoria.rentalSup.dto;


public record UserTypeFilter(int limit,
                             int offset,
                             String lastName,
                             String number
) {

}
