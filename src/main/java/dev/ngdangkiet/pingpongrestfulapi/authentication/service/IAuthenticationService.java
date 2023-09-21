package dev.ngdangkiet.pingpongrestfulapi.authentication.service;

import dev.ngdangkiet.pingpongrestfulapi.authentication.payload.AuthenticationRequest;
import dev.ngdangkiet.pingpongrestfulapi.authentication.payload.AuthenticationResponse;

/**
 * @author ngdangkiet
 * @since 9/21/2023
 */

public interface IAuthenticationService {
    AuthenticationResponse login(AuthenticationRequest authenticationRequest);
}
