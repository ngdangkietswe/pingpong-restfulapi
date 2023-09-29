package dev.ngdangkiet.pingpongrestfulapi.authentication.payload;

import dev.ngdangkiet.pingpongrestfulapi.validation.ValidationPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * @author ngdangkiet
 * @since 9/21/2023
 */

public record AuthenticationRequest(
        @NotBlank(message = "Username shouldn't be null or empty!")
        @Email(message = "Invalid email!")
        String username,
        @NotBlank(message = "Password shouldn't be null or empty!")
        String password
) {
}
