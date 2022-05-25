package com.john.user.client.config;

import com.john.user.client.interceptor.AuthUserInterceptor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author john
 */
@Configuration
public class ClientWebMvcConfig implements WebMvcConfigurer {
    private final ApplicationContext applicationContext;

    public ClientWebMvcConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthUserInterceptor(applicationContext)).excludePathPatterns("/auth/login");
    }
}
