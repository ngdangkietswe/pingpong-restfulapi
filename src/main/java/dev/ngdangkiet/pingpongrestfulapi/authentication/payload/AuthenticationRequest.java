package dev.ngdangkiet.pingpongrestfulapi.authentication.payload;

/**
 * @author ngdangkiet
 * @since 9/21/2023
 */

public record AuthenticationRequest(
        String username,
        String password
) {
}
