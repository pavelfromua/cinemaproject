package com.dev.cinema.validator;

import com.dev.cinema.annotation.PasswordsValueMatch;
import com.dev.cinema.model.dto.UserRequestDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordsValueMatchValidator implements ConstraintValidator<PasswordsValueMatch,
        UserRequestDto> {
    @Override
    public boolean isValid(UserRequestDto requestDto,
                           ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
