package dev.ngdangkiet.pingpongrestfulapi.authentication.service;

import dev.ngdangkiet.pingpongrestfulapi.authentication.payload.AuthenticationRequest;
import dev.ngdangkiet.pingpongrestfulapi.authentication.payload.AuthenticationResponse;
import dev.ngdangkiet.pingpongrestfulapi.customer.model.CustomerDTO;
import dev.ngdangkiet.pingpongrestfulapi.customer.model.CustomerEntity;
import dev.ngdangkiet.pingpongrestfulapi.customer.model.CustomerMapper;
import dev.ngdangkiet.pingpongrestfulapi.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * @author ngdangkiet
 * @since 9/21/2023
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationImpl implements IAuthenticationService {
    private final CustomerMapper customerMapper;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.username(),
                            authenticationRequest.password()
                    )
            );
            CustomerEntity customerEntity = (CustomerEntity) authentication.getPrincipal();
            CustomerDTO customerDTO = customerMapper.apply(customerEntity);

            return new AuthenticationResponse(jwtUtil.issueToken(customerDTO.email()), customerDTO);
        } catch (BadCredentialsException ex) {
            log.error("Login failed: {}", ex.getMessage());
            throw new BadCredentialsException("Invalid username or password!");
        }
    }
}
