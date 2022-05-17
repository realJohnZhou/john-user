package com.john.user.app.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author john
 */
@Slf4j
@Component
public class JwtUtil {
    private static final String BLOCK_TOKEN_PREFIX = "BLOCK:TOKEN:";
    private final JwtProperties jwtProperties;
    private final RedissonClient redissonClient;

    public JwtUtil(JwtProperties jwtProperties,
                   RedissonClient redissonClient) {
        this.jwtProperties = jwtProperties;
        this.redissonClient = redissonClient;
    }

    public String createToken(String username) throws UnsupportedEncodingException {
        JWTCreator.Builder builder = JWT.create();
        return builder
                .withJWTId(UUID.randomUUID().toString())
                .withSubject(username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + (jwtProperties.getAccessExpireTime() * 1000 * 60)))
                .sign(Algorithm.HMAC512(jwtProperties.getSecret()));
    }

    public String validateToken(String token) {
        if (this.notBlock(token)) {
            try {
                JWTVerifier verifier = JWT.require(Algorithm.HMAC512(jwtProperties.getSecret())).build();
                return verifier.verify(token).getSubject();
            } catch (Exception e) {
                log.error("validate token error: {}", e.getMessage(), e);
            }
        }
        return null;
    }

    public void blockToken(String accessToken) {
        if (StringUtils.hasText(accessToken)) {
            RBucket<String> bockTokenBucket = redissonClient.getBucket(BLOCK_TOKEN_PREFIX + accessToken);
            bockTokenBucket.set("");
            bockTokenBucket.expire(jwtProperties.getAccessExpireTime(), TimeUnit.MINUTES);
        }
    }

    public boolean notBlock(String accessToken) {
        RBucket<String> bockTokenBucket = redissonClient.getBucket(BLOCK_TOKEN_PREFIX + accessToken);
        return !bockTokenBucket.isExists();
    }
}
