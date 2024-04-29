package com.viktoria.rentalSup.validator;

public interface Validator<T> {
    ValidationResult isValid(T object);
}
