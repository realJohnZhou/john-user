package com.john.user.client.dto.response;

import lombok.Data;

/**
 * @author john
 */
@Data
public class LoginResponse {
    private String userId;

    private String username;

    private String accessToken;

    private String refreshToken;
}
