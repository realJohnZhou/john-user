package com.john.user.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.john.user.app.entity.Permission;

import java.util.Set;

/**
 * @author john
 */
public interface PermissionService extends IService<Permission> {

    /**
     * 修改角色权限
     *
     * @param roleId  角色主键
     * @param menuIds 权限
     */
    void replace(String roleId, Set<String> menuIds);
}
