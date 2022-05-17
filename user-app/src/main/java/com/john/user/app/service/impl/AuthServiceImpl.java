package com.john.user.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.john.boot.common.dto.AuditUser;
import com.john.boot.common.exception.BusinessException;
import com.john.boot.common.util.AuditUserContextHolder;
import com.john.user.app.entity.User;
import com.john.user.app.jwt.JwtProperties;
import com.john.user.app.jwt.JwtUtil;
import com.john.user.app.mapper.UserMapper;
import com.john.user.app.security.PasswordCryptUtil;
import com.john.user.app.service.AuthService;
import com.john.user.client.dto.request.LoginRequest;
import com.john.user.client.dto.response.AuthInfoResponse;
import com.john.user.client.dto.response.LoginResponse;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.UnsupportedEncodingException;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author john
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final static String USER_REFRESH_TOKEN = "USER:%s:REFRESH_TOKEN";
    private final static String USER_TOKEN = "USER:%s:TOKEN";

    private final JwtUtil jwtUtil;
    private final UserMapper userMapper;
    private final RedissonClient redissonClient;
    private final JwtProperties jwtProperties;

    public AuthServiceImpl(JwtUtil jwtUtil,
                           UserMapper userMapper,
                           RedissonClient redissonClient,
                           JwtProperties jwtProperties) {
        this.jwtUtil = jwtUtil;
        this.userMapper = userMapper;
        this.redissonClient = redissonClient;
        this.jwtProperties = jwtProperties;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUsername, loginRequest.getUsername());
        User user = userMapper.selectOne(queryWrapper);
        Assert.notNull(user, "user not exist");
        boolean match = PasswordCryptUtil.match(loginRequest.getPassword(), user.getPassword());
        Assert.state(match, "password wrong");
        return this.generateLoginResponse(user);
    }

    @Override
    public LoginResponse refresh(String userId, String refreshToken) {
        User user = userMapper.selectById(userId);
        Assert.notNull(user, "user not exist");
        RBucket<String> refreshTokenBucket = redissonClient.getBucket(String.format(USER_REFRESH_TOKEN, userId));
        Assert.state(refreshTokenBucket.isExists(), "refresh token expired");
        String cacheRefreshToken = refreshTokenBucket.get();
        Assert.state(Objects.equals(cacheRefreshToken, refreshToken), "wrong refresh token");
        refreshTokenBucket.delete();
        RBucket<String> accessTokenBucket = redissonClient.getBucket(String.format(USER_TOKEN, user.getId()));
        String accessToken = accessTokenBucket.getAndDelete();
        if (accessToken != null) {
            jwtUtil.blockToken(accessToken);
        }
        return this.generateLoginResponse(user);
    }

    @Override
    public void logout(String userId) {
        RBucket<Object> refreshTokenBucket = redissonClient.getBucket(String.format(USER_REFRESH_TOKEN, userId));
        refreshTokenBucket.delete();
        RBucket<String> accessTokenBucket = redissonClient.getBucket(String.format(USER_TOKEN, userId));
        String accessToken = accessTokenBucket.getAndDelete();
        if (accessToken != null) {
            jwtUtil.blockToken(accessToken);
        }
    }

    @Override
    public AuthInfoResponse getMe() {
        AuditUser auditUser = AuditUserContextHolder.getUser();
        Assert.notNull(auditUser, "login first");
        User user = this.userMapper.selectById(auditUser.getUserId());
        AuthInfoResponse authInfoResponse = new AuthInfoResponse();
        BeanUtils.copyProperties(user, auditUser);
        return authInfoResponse;
    }

    private LoginResponse generateLoginResponse(User user) {
        try {
            LoginResponse loginResponse = new LoginResponse();
            String accessToken = jwtUtil.createToken(user.getUsername());
            loginResponse.setUserId(user.getId());
            loginResponse.setUsername(user.getUsername());
            loginResponse.setAccessToken(accessToken);
            String refreshToken = UUID.randomUUID().toString();
            loginResponse.setRefreshToken(refreshToken);
            // set cache
            RBucket<Object> accessTokenBucket = redissonClient.getBucket(String.format(USER_TOKEN, user.getId()));
            accessTokenBucket.set(accessToken);
            accessTokenBucket.expire(jwtProperties.getAccessExpireTime(), TimeUnit.MINUTES);
            RBucket<Object> refreshTokenBucket = redissonClient.getBucket(String.format(USER_REFRESH_TOKEN, user.getId()));
            refreshTokenBucket.set(refreshToken);
            refreshTokenBucket.expire(jwtProperties.getRefreshExpireTime(), TimeUnit.MINUTES);
            return loginResponse;
        } catch (UnsupportedEncodingException e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
