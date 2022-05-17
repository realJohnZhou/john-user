package com.john.user.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.john.user.app.entity.Role;
import com.john.user.app.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author john
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {
    /**
     * get roles by user id
     *
     * @param userId user id
     * @return role list
     */
    List<Role> getByUserId(@Param("userId") String userId);
}
