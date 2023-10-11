package dev.ngdangkiet.pingpongrestfulapi.validation;

import dev.ngdangkiet.pingpongrestfulapi.common.Translator;
import dev.ngdangkiet.pingpongrestfulapi.customer.model.Gender;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Locale;

/**
 * @author ngdangkiet
 * @since 9/25/2023
 */

@RequiredArgsConstructor
public class GenderValidator implements ConstraintValidator<ValidationGender, String> {
    private final Translator translator;

    @Override
    public boolean isValid(String gender, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = Arrays.stream(Gender.values()).anyMatch(g -> g.toString().equalsIgnoreCase(gender));

        if (!isValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(translator.translate("customer.gender.invalid_vi", new Locale("vi", "VI")))
                    .addConstraintViolation();
        }

        return isValid;
    }
}
