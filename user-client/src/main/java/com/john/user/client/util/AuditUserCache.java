package com.john.user.client.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.john.boot.common.dto.AuditUser;

import java.util.concurrent.TimeUnit;

/**
 * @author john
 */
public class AuditUserCache {
    private static final Cache<String, AuditUser> AUDIT_USER_CACHE = CacheBuilder.newBuilder().expireAfterWrite(10, TimeUnit.MINUTES).build();

    public static void set(String token, AuditUser auditUser) {
        AUDIT_USER_CACHE.put(token, auditUser);
    }

    public static AuditUser get(String token) {
        return AUDIT_USER_CACHE.getIfPresent(token);
    }
}
