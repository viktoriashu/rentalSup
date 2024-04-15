package com.viktoria.rentalSup.service;

import com.viktoria.rentalSup.dao.UserTypeDao;
import com.viktoria.rentalSup.dto.UserTypeDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class UserTypeService {

    private static final UserTypeService INSTANCE = new UserTypeService();

    private final UserTypeDao userTypeDao = UserTypeDao.getInstance();

    private UserTypeService() {
    }

    public List<UserTypeDto> findAll(){
        return userTypeDao.findAll().stream()
                .map(userType -> new UserTypeDto(
                        userType.getId(),
                        """
                                %s- %s- %s- %s-
                                """.formatted(userType.getFirstName(), userType.getLastName(), userType.getLogin(), userType.getRole())
                ))
                .collect(toList());

    }

    public static UserTypeService getInstance() {
        return INSTANCE;
    }
}