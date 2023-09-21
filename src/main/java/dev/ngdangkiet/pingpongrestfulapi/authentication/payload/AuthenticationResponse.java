package dev.ngdangkiet.pingpongrestfulapi.authentication.payload;

import dev.ngdangkiet.pingpongrestfulapi.customer.model.CustomerDTO;

/**
 * @author ngdangkiet
 * @since 9/21/2023
 */

public record AuthenticationResponse(
        String accessToken,
        CustomerDTO customer
) {
}
