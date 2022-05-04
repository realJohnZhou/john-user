package com.john.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.john.user.entity.User;
import com.john.user.mapper.UserMapper;
import com.john.user.service.UserService;
import org.springframework.stereotype.Service;

/**
 * user service
 *
 * @author john
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
