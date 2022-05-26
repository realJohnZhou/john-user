package com.john.user.app.jwt;

import com.john.boot.common.dto.AuthUser;
import com.john.user.app.entity.Role;
import com.john.user.app.entity.User;
import com.john.user.app.service.UserRoleService;
import com.john.user.app.service.UserService;
import com.john.user.client.contants.Constants;
import com.john.user.client.util.AuthUserCache;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author john
 */
@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final UserRoleService userRoleService;

    public JwtFilter(JwtUtil jwtUtil,
                     UserService userService,
                     UserRoleService userRoleService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isEmpty(header) || !header.startsWith(Constants.JWT_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }
        final String token = header.split(Constants.JWT_PREFIX)[1].trim();
        String subject = jwtUtil.validateToken(token);
        if (StringUtils.hasText(subject)) {
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                User user = userService.findByUsername(subject);
                if (user == null) {
                    throw new UsernameNotFoundException(String.format("User: %s, not found", subject));
                }
                this.cacheAuthInfo(token, user);
                UserDetails userDetails = this.buildUserDetails(subject, user);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    private void cacheAuthInfo(String token, User user) {
        AuthUser authInfo = new AuthUser();
        BeanUtils.copyProperties(user, authInfo);
        authInfo.setUserId(user.getId());
        AuthUserCache.set(token, authInfo);
    }

    private UserDetails buildUserDetails(String subject, User user) {
        List<Role> roles = userRoleService.getByUserId(user.getId());
        String[] authorities = roles.stream().map(Role::getCode).toArray(String[]::new);
        org.springframework.security.core.userdetails.User.UserBuilder userBuilder = org.springframework.security.core.userdetails.User.builder();
        userBuilder.username(subject).password(user.getPassword()).authorities(authorities);
        return userBuilder.build();
    }
}
