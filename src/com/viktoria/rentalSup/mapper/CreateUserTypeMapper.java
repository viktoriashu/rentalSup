package com.viktoria.rentalSup.mapper;

import com.viktoria.rentalSup.dao.impl.RoleDao;
import com.viktoria.rentalSup.dto.UserTypeDto.CreateUserTypeDto;
import com.viktoria.rentalSup.entity.Role;
import com.viktoria.rentalSup.entity.UserType;
import lombok.NoArgsConstructor;

import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateUserTypeMapper implements Mapper<CreateUserTypeDto, UserType> {

    private static final CreateUserTypeMapper INSTANCE = new CreateUserTypeMapper();

    public static CreateUserTypeMapper getInstance() {
        return INSTANCE;
    }

    private final RoleDao roleDao = RoleDao.getInstance();

    @Override
    public UserType mapFrom(CreateUserTypeDto object) {
        String roleName = null;
        Optional<Role> optionalRole = roleDao.findById(Integer.parseInt(object.getRoleId()));
        if (optionalRole.isPresent()) {
            roleName = optionalRole.get().getRoleName();
        }
        return UserType.builder()
                .firstName(object.getFirstName())
                .lastName(object.getLastName())
                .login(object.getLogin())
                .password(object.getPassword())
                .number(object.getNumber())
                .role(Role.builder()
                        .id(Integer.parseInt(object.getRoleId()))
                        .roleName(roleName)
                        .build())
                .build();
    }
}
