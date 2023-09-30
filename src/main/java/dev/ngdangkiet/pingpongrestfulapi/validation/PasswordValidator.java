package dev.ngdangkiet.pingpongrestfulapi.validation;

import dev.ngdangkiet.pingpongrestfulapi.common.PingPongConstant;
import dev.ngdangkiet.pingpongrestfulapi.common.Translator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.Locale;
import java.util.regex.Pattern;

/**
 * @author ngdangkiet
 * @since 9/29/2023
 */

@RequiredArgsConstructor
public class PasswordValidator implements ConstraintValidator<ValidationPassword, String> {
    private final Translator translator;

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = StringUtils.hasText(password)
                && password.length() >= PingPongConstant.Password.MIN_SIZE
                && password.length() <= PingPongConstant.Password.MAX_SIZE
                && Pattern.matches(PingPongConstant.Password.PATTERN_PASSWORD, password);

        if (!isValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(translator.translate("customer.password.invalid_vi", new Locale("vi", "VI")))
                    .addConstraintViolation();
        }

        return isValid;
    }
}
