package dev.ngdangkiet.pingpongrestfulapi.customer.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author ngdangkiet
 * @since 9/20/2023
 */

@Component
public class CustomerMapper implements Function<CustomerEntity, CustomerDTO> {

    @Override
    public CustomerDTO apply(CustomerEntity entity) {
        return new CustomerDTO(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getGender().name(),
                entity.getAge(),
                entity.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList())
        );
    }
}
