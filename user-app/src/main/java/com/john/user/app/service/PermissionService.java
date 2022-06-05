package com.john.user.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.john.user.app.entity.Permission;

import java.util.List;

/**
 * @author john
 */
public interface PermissionService extends IService<Permission> {

    /**
     * 修改角色权限
     *
     * @param permissions 权限
     */
    void replace(List<Permission> permissions);
}
