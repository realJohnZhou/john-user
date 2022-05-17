package com.john.user.app.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.john.boot.mysql.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author john
 */
@TableName("role")
@EqualsAndHashCode(callSuper = true)
@Data
public class Role extends BaseEntity {
    @TableField("code")
    private String code;

    @TableField("name")
    private String name;
}
