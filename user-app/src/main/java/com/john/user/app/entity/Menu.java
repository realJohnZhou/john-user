package com.john.user.app.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.john.boot.mysql.entity.BaseEntity;
import com.john.user.app.enumeration.MenuTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author john
 */
@TableName("menu")
@EqualsAndHashCode(callSuper = true)
@Data
public class Menu extends BaseEntity {

    @TableField("code")
    private String code;

    @TableField("name")
    private String name;

    @TableField("path")
    private String path;

    @TableField("type")
    private MenuTypeEnum type;

    @TableField("parent")
    private String parent;

    @TableField("icon")
    private String icon;
}
