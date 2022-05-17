package com.john.user.app.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.john.boot.mysql.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author john
 */
@EqualsAndHashCode(callSuper = true)
@TableName("user_role")
@Data
public class UserRole extends BaseEntity {
    @TableField("user_id")
    private String userId;

    @TableField("role_id")
    private String roleId;
}
