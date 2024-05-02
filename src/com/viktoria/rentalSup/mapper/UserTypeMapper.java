package com.viktoria.rentalSup.mapper;

import com.viktoria.rentalSup.dao.impl.RoleDao;
import com.viktoria.rentalSup.dto.UserTypeDto.UserTypeDto;
import com.viktoria.rentalSup.entity.Role;
import com.viktoria.rentalSup.entity.UserType;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapping;

import java.util.Optional;

import static lombok.AccessLevel.*;


@NoArgsConstructor(access = PRIVATE)
public class UserTypeMapper implements Mapper<UserType, UserTypeDto> {
    private static final UserTypeMapper INSTANCE = new UserTypeMapper();

    public static UserTypeMapper getInstance() {
        return INSTANCE;
    }
    private final RoleDao roleDao = RoleDao.getInstance();



    @Override
    public UserTypeDto mapFrom(UserType object) {

        return UserTypeDto.builder()
                .id(object.getId())
                .firstName(object.getFirstName())
                .lastName(object.getLastName())
                .login(object.getLogin())
                .number(object.getNumber())
                .role(Role.builder()
                        .id(object.getRole().getId())
                        .roleName(object.getRole().getRoleName())
                        .build())
                .build();
    }


}
