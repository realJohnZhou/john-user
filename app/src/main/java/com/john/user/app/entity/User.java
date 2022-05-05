package com.john.user.app.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.john.boot.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * user entity
 * @author john
 */
@EqualsAndHashCode(callSuper = true)
@TableName("user")
@Data
public class User extends BaseEntity {
    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("name")
    private String name;

    @TableField("phone")
    private String phone;

    @TableField("email")
    private String email;
}
