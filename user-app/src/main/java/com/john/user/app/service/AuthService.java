package com.john.user.app.service;

import com.john.boot.common.dto.AuthUser;
import com.john.user.client.dto.request.LoginRequest;
import com.john.user.client.dto.response.LoginResponse;

/**
 * @author john
 */
public interface AuthService {
    /**
     * login
     *
     * @param loginRequest login request
     * @return
     */
    LoginResponse login(LoginRequest loginRequest);

    /**
     * refresh access token
     *
     * @param userId       user id
     * @param refreshToken refresh token
     * @return
     */
    LoginResponse refresh(String userId, String refreshToken);

    /**
     * logout
     *
     * @param userId user id
     */
    void logout(String userId);

    /**
     * get login info
     *
     * @return
     */
    AuthUser getMe();
}
