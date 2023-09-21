package dev.ngdangkiet.pingpongrestfulapi.customer.payload;

/**
 * @author ngdangkiet
 * @since 9/20/2023
 */

public record CustomerUpdateRequest(
        String name,
        String email,
        Integer age
) {
}
