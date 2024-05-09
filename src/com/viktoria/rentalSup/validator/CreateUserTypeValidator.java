package com.viktoria.rentalSup.validator;

import com.viktoria.rentalSup.dto.UserTypeDto.CreateUserTypeDto;

import lombok.NoArgsConstructor;

import java.util.ResourceBundle;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateUserTypeValidator implements Validator<CreateUserTypeDto> {
    private static final String CODE_FIRST_NAME = "error.validator.code.firstName";
    private static final String MESSAGE_FIRST_NAME = "error.validator.message.firstName";
    private static final String CODE_LAST_NAME = "error.validator.code.lastName";
    private static final String MESSAGE_LAST_NAME = "error.validator.message.lastName";
    private static final String CODE_LOGIN = "error.validator.code.login";
    private static final String MESSAGE_LOGIN = "error.validator.message.login";
    private static final String CODE_PASSWORD = "error.validator.code.password";
    private static final String MESSAGE_PASSWORD = "error.validator.message.password";
    private static final String CODE_NUMBER = "error.validator.code.number";
    private static final String MESSAGE_NUMBER = "error.validator.message.number";

    private static final CreateUserTypeValidator INSTANCE = new CreateUserTypeValidator();

    public static CreateUserTypeValidator getInstance() {
        return INSTANCE;
    }

//Закомментированные ошибки удалить при реализации локалазации или восстановить, при провале

    @Override
    public ValidationResult isValid(CreateUserTypeDto object) {
        var translations = ResourceBundle.getBundle("translations");

        var validationResult = new ValidationResult();

        if (object.getFirstName().isEmpty()) {
            validationResult.add(Error.of(translations.getString(CODE_FIRST_NAME), translations.getString(MESSAGE_FIRST_NAME)));
//            validationResult.add(Error.of("invalid.firstName", "First name is invalid. " +
//                                                               "The field is not filled"));
        }

        if (object.getLastName().isEmpty()) {
            validationResult.add(Error.of(translations.getString(CODE_LAST_NAME), translations.getString(MESSAGE_LAST_NAME)));
//            validationResult.add(Error.of("invalid.lastName", "Last name is invalid. " +
//                                                              "The field is not filled"));
        }

        if (object.getLogin().isEmpty()) {
            validationResult.add(Error.of(translations.getString(CODE_LOGIN), translations.getString(MESSAGE_LOGIN)));
//            validationResult.add(Error.of("invalid.login", "Login is invalid. The field is not filled"));
        }

        if (object.getPassword().isEmpty()) {
            validationResult.add(Error.of(translations.getString(CODE_PASSWORD), translations.getString(MESSAGE_PASSWORD)));
//            validationResult.add(Error.of("invalid.password", "Password is invalid. The field is not filled"));
        }

        if (object.getNumber().isEmpty()) {
            validationResult.add(Error.of(translations.getString(CODE_NUMBER), translations.getString(MESSAGE_NUMBER)));
//            validationResult.add(Error.of("invalid.number", "Number is invalid. The field is not filled"));
        }

        return validationResult;
    }

}
