package dev.ngdangkiet.pingpongrestfulapi.exception;

import java.time.LocalDateTime;

/**
 * @author ngdangkiet
 * @since 9/20/2023
 */

public record ApiError(
        String path,
        String message,
        int statusCode,
        LocalDateTime localDateTime
) {
}
