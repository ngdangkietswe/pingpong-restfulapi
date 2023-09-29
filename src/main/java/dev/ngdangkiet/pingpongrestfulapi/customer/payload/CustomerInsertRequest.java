package dev.ngdangkiet.pingpongrestfulapi.customer.payload;

import dev.ngdangkiet.pingpongrestfulapi.common.PingPongConstant;
import dev.ngdangkiet.pingpongrestfulapi.validation.ValidationGender;
import dev.ngdangkiet.pingpongrestfulapi.validation.ValidationPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * @author ngdangkiet
 * @since 9/21/2023
 */

public record CustomerInsertRequest(
        @NotBlank(message = "Name shouldn't be null or empty!")
        String name,
        @Email(message = "Invalid email!")
        String email,
        @ValidationPassword
        String password,
        @ValidationGender
        String gender, // MALE OR FEMALE OR OTHER
        Integer age
) {
}
