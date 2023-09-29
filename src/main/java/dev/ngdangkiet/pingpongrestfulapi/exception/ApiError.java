package dev.ngdangkiet.pingpongrestfulapi.exception;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author ngdangkiet
 * @since 9/20/2023
 */

public record ApiError(
        String path,
        String message,
        int statusCode,
        Map<String, String> errors,
        LocalDateTime localDateTime
) {
    public ApiError(String path, String message, int statusCode, Map<String, String> errors) {
        this(
                path,
                message,
                statusCode,
                errors,
                LocalDateTime.now()
        );
    }
}
