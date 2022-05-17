package com.john.user.client.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author john
 */
@Data
@ConfigurationProperties(prefix = "user")
public class UserProperties {
    private String url;
}
