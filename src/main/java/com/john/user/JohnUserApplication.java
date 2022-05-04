package com.john.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author john
 */
@MapperScan("com.john.user.mapper")
@SpringBootApplication
public class JohnUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(JohnUserApplication.class, args);
    }

}
