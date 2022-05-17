package com.john.user.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.john.user.app.entity.Role;
import com.john.user.app.entity.UserRole;
import com.john.user.app.mapper.UserRoleMapper;
import com.john.user.app.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author john
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
    @Override
    public List<Role> getByUserId(String userId) {
        return this.baseMapper.getByUserId(userId);
    }
}
