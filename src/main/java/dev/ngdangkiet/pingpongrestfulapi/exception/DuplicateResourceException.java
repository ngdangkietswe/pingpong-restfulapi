package dev.ngdangkiet.pingpongrestfulapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author ngdangkiet
 * @since 9/20/2023
 */

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(Object key, Object value) {
        super(String.format("Conflict %s with value %s", key, value));
    }
}
