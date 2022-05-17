package com.john.user.app.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author john
 */
@Data
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String secret;
    /**
     * minute
     */
    private Long accessExpireTime;
    /**
     * minute
     */
    private Long refreshExpireTime;

    public JwtProperties() {
        this.secret = "secret";
        this.accessExpireTime = 60L;
        this.refreshExpireTime = (long) (24 * 60);
    }
}
