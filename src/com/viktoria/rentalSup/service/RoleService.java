package com.viktoria.rentalSup.service;

import com.viktoria.rentalSup.dao.impl.RoleDao;
import com.viktoria.rentalSup.dto.RoleDto;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static lombok.AccessLevel.*;

@NoArgsConstructor(access = PRIVATE)
public class RoleService {
    private static final RoleService INSTANCE = new RoleService();

    public static RoleService getInstance() {
        return INSTANCE;
    }

    private final RoleDao roleDao = RoleDao.getInstance();

    public List<RoleDto> findAll() {
        return roleDao.findAll().stream()
                .map(role -> RoleDto.builder()
                        .id(String.valueOf(role.getId()))
                        .roleName(role.getRoleName())
                        .build())
                .collect(toList());
    }

}
