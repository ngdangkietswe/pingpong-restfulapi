package dev.ngdangkiet.pingpongrestfulapi.validation;

import dev.ngdangkiet.pingpongrestfulapi.customer.model.Gender;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

/**
 * @author ngdangkiet
 * @since 9/25/2023
 */

public class GenderValidator implements ConstraintValidator<ValidationGender, String> {

    @Override
    public boolean isValid(String gender, ConstraintValidatorContext constraintValidatorContext) {
        return Arrays.stream(Gender.values()).anyMatch(g -> g.toString().equalsIgnoreCase(gender));
    }
}
