package com.john.user.app.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.john.boot.mysql.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author john
 */
@TableName("permission")
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
public class Permission extends BaseEntity {
    @TableField("role_id")
    private String roleId;

    @TableField("menu_id")
    private String menuId;
}
