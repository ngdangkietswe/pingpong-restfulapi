package dev.ngdangkiet.pingpongrestfulapi.customer.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * @author ngdangkiet
 * @since 9/20/2023
 */

public record CustomerUpdateRequest(
        @NotBlank(message = "Name shouldn't be null or empty!")
        String name,
        @Email(message = "Invalid email!")
        String email,
        Integer age
) {
}
