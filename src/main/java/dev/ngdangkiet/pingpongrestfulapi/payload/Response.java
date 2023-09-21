package dev.ngdangkiet.pingpongrestfulapi.payload;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * @author ngdangkiet
 * @since 9/20/2023
 */

public record Response<D>(
        int statusCode,
        D data,
        LocalDateTime localDateTime
) {
    public Response(D data) {
        this(
                HttpStatus.OK.value(),
                data,
                LocalDateTime.now()
        );
    }
}
