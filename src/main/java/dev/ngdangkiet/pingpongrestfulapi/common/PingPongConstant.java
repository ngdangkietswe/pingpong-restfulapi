package dev.ngdangkiet.pingpongrestfulapi.common;

/**
 * @author ngdangkiet
 * @since 9/29/2023
 */

public class PingPongConstant {
    public static class Password {
        public static final int MIN_SIZE = 8;
        public static final int MAX_SIZE = 16;
        public static final String PATTERN_PASSWORD = "^(?=.*[!@#$%^&*()_+{}\\[\\]:;<>,.?~\\-])(?=.*[A-Za-z])(?=.*\\d).+$";
    }
}
