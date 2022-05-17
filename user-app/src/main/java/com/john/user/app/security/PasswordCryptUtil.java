package com.john.user.app.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author john
 */
public class PasswordCryptUtil {
    public final static BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();

    public static String encode(String password) {
        return ENCODER.encode(password);
    }

    public static boolean match(String rawPassword, String encodedPassword) {
        return ENCODER.matches(rawPassword, encodedPassword);
    }
}
