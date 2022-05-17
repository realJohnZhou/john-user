package com.john.user.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.john.user.app.entity.Role;
import com.john.user.app.entity.UserRole;

import java.util.List;

/**
 * @author john
 */
public interface UserRoleService extends IService<UserRole> {
    /**
     * get roles by user id
     * @param userId user id
     * @return role list
     */
    List<Role> getByUserId(String userId);
}
