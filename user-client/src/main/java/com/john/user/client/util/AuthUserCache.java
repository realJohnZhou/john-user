package com.john.user.client.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.john.boot.common.dto.AuthUser;

import java.util.concurrent.TimeUnit;

/**
 * @author john
 */
public class AuthUserCache {
    private static final Cache<String, AuthUser> AUDIT_USER_CACHE = CacheBuilder.newBuilder().expireAfterWrite(10, TimeUnit.MINUTES).build();

    public static void set(String token, AuthUser authInfo) {
        AUDIT_USER_CACHE.put(token, authInfo);
    }

    public static AuthUser get(String token) {
        return AUDIT_USER_CACHE.getIfPresent(token);
    }
}
