package dev.ngdangkiet.pingpongrestfulapi.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ngdangkiet
 * @since 9/20/2023
 */

@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception ex, HttpServletRequest request) {
        return new ResponseEntity<>(
                new ApiError(
                        request.getRequestURI(),
                        ex.getMessage(),
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        Collections.emptyMap()
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiError> handleException(BadCredentialsException ex, HttpServletRequest request) {
        return new ResponseEntity<>(
                new ApiError(
                        request.getRequestURI(),
                        ex.getMessage(),
                        HttpStatus.UNAUTHORIZED.value(),
                        Collections.emptyMap()
                ),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleException(ResourceNotFoundException ex, HttpServletRequest request) {
        return new ResponseEntity<>(
                new ApiError(
                        request.getRequestURI(),
                        ex.getMessage(),
                        HttpStatus.NOT_FOUND.value(),
                        Collections.emptyMap()
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiError> handleException(DuplicateResourceException ex, HttpServletRequest request) {
        return new ResponseEntity<>(
                new ApiError(
                        request.getRequestURI(),
                        ex.getMessage(),
                        HttpStatus.CONFLICT.value(),
                        Collections.emptyMap()
                ),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(RequestValidationException.class)
    public ResponseEntity<ApiError> handleException(RequestValidationException ex, HttpServletRequest request) {
        return new ResponseEntity<>(
                new ApiError(
                        request.getRequestURI(),
                        ex.getMessage(),
                        HttpStatus.BAD_REQUEST.value(),
                        Collections.emptyMap()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(
                new ApiError(
                        request.getRequestURI(),
                        "Client error!",
                        HttpStatus.BAD_REQUEST.value(),
                        errorMap
                ),
                HttpStatus.BAD_REQUEST
        );
    }
}
