package com.br.app.vehicles.java.infra.controller.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

public class MarkValidator implements ConstraintValidator<MarkValid, String> {

    @Override
    public boolean isValid(final String value, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(value)) {
            return false;
        }
        return MarkEnum.isValid(value);
    }
}