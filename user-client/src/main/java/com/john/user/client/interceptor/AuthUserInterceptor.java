package com.john.user.client.interceptor;

import com.john.boot.common.dto.AuthUser;
import com.john.boot.common.dto.Result;
import com.john.boot.common.util.AuditUserContextHolder;
import com.john.user.client.clients.UserClient;
import com.john.user.client.contants.Constants;
import com.john.user.client.util.AuthUserCache;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author john
 */
public class AuthUserInterceptor implements HandlerInterceptor {
    private final ApplicationContext applicationContext;

    public AuthUserInterceptor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(authorization) && authorization.startsWith(Constants.JWT_PREFIX)) {
            String token = authorization.split(Constants.JWT_PREFIX)[1];
            AuthUser authUser = AuthUserCache.get(token);
            if (authUser == null) {
                Result<AuthUser> me = applicationContext.getBean(UserClient.class).getMe();
                if (Result.OK.equals(me.getCode())) {
                    authUser = me.getData();
                    AuthUserCache.set(token, authUser);
                    AuditUserContextHolder.setUser(authUser);
                }
            } else {
                AuditUserContextHolder.setUser(authUser);
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        AuditUserContextHolder.remove();
    }
}
