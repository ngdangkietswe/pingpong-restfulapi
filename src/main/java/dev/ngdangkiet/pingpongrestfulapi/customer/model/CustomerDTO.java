package dev.ngdangkiet.pingpongrestfulapi.customer.model;

import java.util.List;

/**
 * @author ngdangkiet
 * @since 9/20/2023
 */


public record CustomerDTO(
        Long id,
        String name,
        String email,
        String gender,
        Integer age,
        List<String> roles
) {
}
