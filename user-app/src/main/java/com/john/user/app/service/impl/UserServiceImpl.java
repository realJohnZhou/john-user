package com.john.user.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.john.user.app.entity.User;
import com.john.user.app.mapper.UserMapper;
import com.john.user.app.security.PasswordCryptUtil;
import com.john.user.app.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * user service
 *
 * @author john
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public User findByUsername(String username) {
        Assert.hasText(username, "username not empty");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUsername, username);
        return this.baseMapper.selectOne(queryWrapper);
    }

    @Override
    public void reset(String id) {
        // todo
        User user = this.baseMapper.selectById(id);
        String defaultPassword = PasswordCryptUtil.encode("123456");
    }
}
