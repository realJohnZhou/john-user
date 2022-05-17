package com.john.user.client.dto.request;

import lombok.Data;

/**
 * @author john
 */
@Data
public class LoginRequest {
    private String username;

    private String password;
}
