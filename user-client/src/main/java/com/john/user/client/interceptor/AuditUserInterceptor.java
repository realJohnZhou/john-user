package com.john.user.client.interceptor;

import com.john.boot.common.dto.AuditUser;
import com.john.boot.common.dto.Result;
import com.john.boot.common.util.AuditUserContextHolder;
import com.john.user.client.clients.UserClient;
import com.john.user.client.contants.Constants;
import com.john.user.client.dto.response.AuthInfoResponse;
import com.john.user.client.util.AuditUserCache;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author john
 */
@Component
public class AuditUserInterceptor implements HandlerInterceptor {
    private final UserClient userClient;

    public AuditUserInterceptor(UserClient userClient) {
        this.userClient = userClient;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(authorization) && authorization.startsWith(Constants.JWT_PREFIX)) {
            String token = authorization.split(Constants.JWT_PREFIX)[1];
            AuditUser auditUser = AuditUserCache.get(token);
            if (auditUser == null) {
                Result<AuthInfoResponse> me = userClient.getMe();
                if (Result.OK.equals(me.getCode())) {
                    AuthInfoResponse authInfo = me.getData();
                    auditUser = new AuditUser(authInfo.getUserId(), authInfo.getName());
                    AuditUserCache.set(token, auditUser);
                }
            }
            AuditUserContextHolder.setUser(auditUser);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        AuditUserContextHolder.remove();
    }
}
