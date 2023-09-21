package dev.ngdangkiet.pingpongrestfulapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ngdangkiet
 * @since 9/21/2023
 */

@RestController
@RequestMapping("/api/ping")
public class PingPongController {
    private static int COUNTER = 0;

    record PingPong(String result) {
    }

    @GetMapping
    public PingPong ping() {
        return new PingPong("Pong: %s".formatted(++COUNTER));
    }
}
