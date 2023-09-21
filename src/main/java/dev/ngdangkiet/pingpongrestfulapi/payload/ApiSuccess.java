package dev.ngdangkiet.pingpongrestfulapi.payload;

import java.time.LocalDateTime;

/**
 * @author ngdangkiet
 * @since 9/20/2023
 */

public record ApiSuccess<D>(
        D data,
        LocalDateTime localDateTime
) {
}
