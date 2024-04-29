package com.viktoria.rentalSup.service;

import com.viktoria.rentalSup.dao.impl.UserTypeDao;
import com.viktoria.rentalSup.dto.UserTypeDto.CreateUserTypeDto;
import com.viktoria.rentalSup.exception.ValidationException;
import com.viktoria.rentalSup.mapper.CreateUserTypeMapper;
import com.viktoria.rentalSup.validator.CreateUserTypeValidator;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserTypeService {

    private static final UserTypeService INSTANCE = new UserTypeService();

    public static UserTypeService getInstance() {
        return INSTANCE;
    }

    private final UserTypeDao userTypeDao = UserTypeDao.getInstance();
    private final CreateUserTypeValidator createUserTypeValidator = CreateUserTypeValidator.getInstance();
    private final CreateUserTypeMapper createUserTypeMapper = CreateUserTypeMapper.getInstance();

    public Long create(CreateUserTypeDto userTypeDto) {
        var validationResult = createUserTypeValidator.isValid(userTypeDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var userTypeEntity = createUserTypeMapper.mapFrom(userTypeDto);
        userTypeDao.save(userTypeEntity);
        return userTypeEntity.getId();
    }
}
