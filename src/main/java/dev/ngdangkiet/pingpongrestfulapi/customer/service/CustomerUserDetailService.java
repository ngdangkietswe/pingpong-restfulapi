package dev.ngdangkiet.pingpongrestfulapi.customer.service;

import dev.ngdangkiet.pingpongrestfulapi.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author ngdangkiet
 * @since 9/20/2023
 */

@Service
@RequiredArgsConstructor
public class CustomerUserDetailService implements UserDetailsService {
    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return customerRepository.findCustomerByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Not found username %s", username)));
    }
}
