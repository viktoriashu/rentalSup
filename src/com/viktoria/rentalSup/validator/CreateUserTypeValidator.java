package com.viktoria.rentalSup.validator;

import com.viktoria.rentalSup.dto.UserTypeDto.CreateUserTypeDto;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateUserTypeValidator implements Validator<CreateUserTypeDto> {

    private static final CreateUserTypeValidator INSTANCE = new CreateUserTypeValidator();

    public static CreateUserTypeValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public ValidationResult isValid(CreateUserTypeDto object) {
        var validationResult = new ValidationResult();

        if (object.getFirstName().isEmpty()) {
            validationResult.add(Error.of("invalid.firstName", "First name is invalid. " +
                                                               "The field is not filled"));
        }

        if (object.getLastName().isEmpty()) {
            validationResult.add(Error.of("invalid.lastName", "Last name is invalid. " +
                                                              "The field is not filled"));
        }

        if (object.getLogin().isEmpty()) {
            validationResult.add(Error.of("invalid.login", "Login is invalid. The field is not filled"));
        }

        if (object.getPassword().isEmpty()) {
            validationResult.add(Error.of("invalid.password", "Password is invalid. The field is not filled"));
        }

        if (object.getNumber().isEmpty()) {
            validationResult.add(Error.of("invalid.number", "Number is invalid. The field is not filled"));
        }

        return validationResult;
    }

}
