package com.john.user.client;

import com.john.user.client.properties.UserProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * auto configuration
 *
 * @author john
 */
@EnableFeignClients
@ComponentScan
@EnableConfigurationProperties(UserProperties.class)
public class UserClientConfiguration {
}
