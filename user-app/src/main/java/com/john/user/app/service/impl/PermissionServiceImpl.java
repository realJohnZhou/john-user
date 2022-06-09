package com.john.user.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.john.user.app.entity.Permission;
import com.john.user.app.mapper.PermissionMapper;
import com.john.user.app.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author john
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    @Override
    public void replace(String roleId, Set<String> menuIds) {
        // todo
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Permission::getRoleId, roleId);
        List<Permission> permissions = this.baseMapper.selectList(queryWrapper);
    }
}
