package com.john.user.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.john.user.app.entity.Permission;
import com.john.user.app.mapper.PermissionMapper;
import com.john.user.app.service.PermissionService;
import org.springframework.stereotype.Service;

/**
 * @author john
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
}
