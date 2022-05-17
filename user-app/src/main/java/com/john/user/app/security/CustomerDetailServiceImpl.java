package com.john.user.app.security;

import com.john.user.app.entity.Role;
import com.john.user.app.entity.User;
import com.john.user.app.service.UserRoleService;
import com.john.user.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author john
 */
@Slf4j
@Service
public class CustomerDetailServiceImpl implements UserDetailsService {

    private final UserService userService;
    private final UserRoleService userRoleService;

    public CustomerDetailServiceImpl(UserService userService,
                                     UserRoleService userRoleService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User: %s, not found", username));
        }
        List<Role> roles = userRoleService.getByUserId(user.getId());
        String[] authorities = roles.stream().map(Role::getCode).toArray(String[]::new);
        org.springframework.security.core.userdetails.User.UserBuilder userBuilder = org.springframework.security.core.userdetails.User.builder();
        userBuilder.username(username).password(user.getPassword()).authorities(authorities);
        return userBuilder.build();
    }
}
