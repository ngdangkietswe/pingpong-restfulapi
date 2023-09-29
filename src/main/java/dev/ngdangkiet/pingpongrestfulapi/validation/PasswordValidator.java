package dev.ngdangkiet.pingpongrestfulapi.validation;

import dev.ngdangkiet.pingpongrestfulapi.common.PingPongConstant;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

/**
 * @author ngdangkiet
 * @since 9/29/2023
 */

public class PasswordValidator implements ConstraintValidator<ValidationPassword, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        return StringUtils.hasText(password)
                && password.length() >= PingPongConstant.Password.MIN_SIZE
                && password.length() <= PingPongConstant.Password.MAX_SIZE
                && Pattern.matches(PingPongConstant.Password.PATTERN_PASSWORD, password);
    }
}
