package dev.ngdangkiet.pingpongrestfulapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author ngdangkiet
 * @since 9/20/2023
 */

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Object key, Object value) {
        super(String.format("Not found %s with value %s", key, value));
    }
}
