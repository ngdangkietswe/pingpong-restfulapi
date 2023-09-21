package dev.ngdangkiet.pingpongrestfulapi.customer.payload;

/**
 * @author ngdangkiet
 * @since 9/21/2023
 */

public record CustomerInsertRequest(
        String name,
        String email,
        String password,
        String gender,
        Integer age
) {
}
