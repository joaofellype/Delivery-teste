package com.teste.delivery.core.domain.shared.validators;

import com.teste.delivery.core.domain.shared.exceptions.DomainException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CustomValidator {

    private CustomValidator() {
        throw new IllegalStateException("Custom Validator");
    }

    public static void validateAndThrow(Object obj) {
        ObjectValidator.isNullAndThrow(obj, "Object validated is null");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> violations = validator.validate(obj);

        List<String> errorMessages = new ArrayList<String>() {
        };
        for (ConstraintViolation<Object> violation : violations) {
            errorMessages.add(violation.getMessage());
        }

        if (!errorMessages.isEmpty())
            throw new DomainException(errorMessages);
    }
}
