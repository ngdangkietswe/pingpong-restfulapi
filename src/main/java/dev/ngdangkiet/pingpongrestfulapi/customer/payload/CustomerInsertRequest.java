package dev.ngdangkiet.pingpongrestfulapi.customer.payload;

import dev.ngdangkiet.pingpongrestfulapi.validation.ValidationGender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * @author ngdangkiet
 * @since 9/21/2023
 */

public record CustomerInsertRequest(
        @NotBlank(message = "Name shouldn't be null or empty!")
        String name,
        @Email(message = "Invalid email!")
        String email,
        @NotBlank(message = "Password shouldn't be null or empty!")
        String password,
        @ValidationGender
        String gender, // MALE OR FEMALE OR OTHER
        Integer age
) {
}
