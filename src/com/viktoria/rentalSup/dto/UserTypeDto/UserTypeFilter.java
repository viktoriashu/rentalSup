package com.viktoria.rentalSup.dto.UserTypeDto;


public record UserTypeFilter(int limit,
                             int offset,
                             String lastName,
                             String number
) {

}
