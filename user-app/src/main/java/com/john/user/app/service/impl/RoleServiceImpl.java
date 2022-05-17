package com.john.user.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.john.user.app.entity.Role;
import com.john.user.app.mapper.RoleMapper;
import com.john.user.app.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * @author john
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
}
