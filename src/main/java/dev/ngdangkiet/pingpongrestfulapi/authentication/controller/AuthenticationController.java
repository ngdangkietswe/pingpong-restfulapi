package dev.ngdangkiet.pingpongrestfulapi.authentication.controller;

import dev.ngdangkiet.pingpongrestfulapi.authentication.payload.AuthenticationRequest;
import dev.ngdangkiet.pingpongrestfulapi.authentication.payload.AuthenticationResponse;
import dev.ngdangkiet.pingpongrestfulapi.authentication.service.IAuthenticationService;
import dev.ngdangkiet.pingpongrestfulapi.payload.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ngdangkiet
 * @since 9/21/2023
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final IAuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AuthenticationRequest authenticationRequest) {
        AuthenticationResponse authenticationResponse = authenticationService.login(authenticationRequest);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, authenticationResponse.accessToken())
                .body(authenticationResponse);
    }
}
