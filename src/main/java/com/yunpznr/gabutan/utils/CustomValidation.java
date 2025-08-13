package com.yunpznr.gabutan.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CustomValidation {
    @Autowired
    private Validator validator;

    public void validate(Object object) {
        Set<ConstraintViolation<Object>> validators = validator.validate(object);
        if (!validators.isEmpty()) {
            throw new ConstraintViolationException(validators);
        }
    }
}
