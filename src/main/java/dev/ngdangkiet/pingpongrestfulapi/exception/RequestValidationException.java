package dev.ngdangkiet.pingpongrestfulapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author ngdangkiet
 * @since 9/20/2023
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequestValidationException extends RuntimeException {
    public RequestValidationException(String message) {
        super(message);
    }
}
