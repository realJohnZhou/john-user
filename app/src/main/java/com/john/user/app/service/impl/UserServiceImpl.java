package com.john.user.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.john.user.app.entity.User;
import com.john.user.app.mapper.UserMapper;
import com.john.user.app.service.UserService;
import org.springframework.stereotype.Service;

/**
 * user service
 *
 * @author john
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
