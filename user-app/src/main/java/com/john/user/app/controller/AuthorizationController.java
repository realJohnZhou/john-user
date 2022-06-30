package com.john.user.app.controller;

import com.john.boot.common.dto.AuthUser;
import com.john.user.app.service.AuthService;
import com.john.user.client.dto.request.LoginRequest;
import com.john.user.client.dto.response.LoginResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author john
 */
@Slf4j
@Api("authorization")
@RestController
@RequestMapping("/auth")
public class AuthorizationController {
    private final AuthService authService;

    public AuthorizationController(AuthService authService) {
        this.authService = authService;
    }

    @ApiOperation("login")
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return this.authService.login(loginRequest);
    }

    @ApiOperation("logout")
    @PostMapping("/logout/{userId}")
    public void logout(@PathVariable String userId) {
        this.authService.logout(userId);
    }

    @PostMapping("/refresh/{userId}")
    public LoginResponse refresh(@PathVariable String userId, @RequestBody String refreshToken) {
        return this.authService.refresh(userId, refreshToken);
    }

    @GetMapping("/get-me")
    public AuthUser getMe() {
        return this.authService.getMe();
    }
}
