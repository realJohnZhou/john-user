package com.john.user.app.jwt;

import com.john.user.client.contants.Constants;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author john
 */
@Component
public class JwtFilter extends OncePerRequestFilter {
    private final UserDetailsService customerDetailServiceImpl;
    private final JwtUtil jwtUtil;
    private final JwtProperties jwtProperties;

    public JwtFilter(UserDetailsService customerDetailServiceImpl,
                     JwtUtil jwtUtil,
                     JwtProperties jwtProperties) {
        this.customerDetailServiceImpl = customerDetailServiceImpl;
        this.jwtUtil = jwtUtil;
        this.jwtProperties = jwtProperties;
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
                UserDetails userDetails = this.customerDetailServiceImpl.loadUserByUsername(subject);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
