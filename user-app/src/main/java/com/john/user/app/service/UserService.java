package com.john.user.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.john.user.app.entity.User;

/**
 * user service
 * @author john
 */
public interface UserService extends IService<User> {

    /**
     * find user by username
     * @param username username
     * @return user
     */
    User findByUsername(String username);
}
