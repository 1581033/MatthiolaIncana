package com.zhao.www.entity.model;

import com.baomidou.mybatisplus.annotation.*;
import com.zhao.www.antui.AntTabel;
import com.zhao.www.base.entity.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Matthiola incana
 * @create 2021/11/10 20:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_menu")
@EqualsAndHashCode(callSuper = true)
public class SysMenu extends BaseModel {

    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;

    @AntTabel(title = "组件路径")
    @TableField("path")
    private String path;

    @TableField("name")
    private String name;

    @TableField("component")
    private String component;

    @TableField("redirect")
    private String redirect;

    @TableField("parent_id")
    private String parentId;

    @TableField("hidden")
    private String hidden;

    @TableField("hide_children_in_menu")
    private String hideChildrenInMenu;

    @AntTabel(title = "菜单名称",sort = 1)
    @TableField("title")
    private String title;

    @AntTabel(title = "图标")
    @TableField("icon")
    private String icon;

    @TableField("keep_alive")
    private String keepAlive;

    @TableField("hidden_header_content")
    private String hiddenHeaderContent;

    @TableField("permission")
    private String permission;

    @TableField(exist = false)
    private List<SysPermission> actionEntitySet;

    @TableField(exist = false)
    private List<SysMenu> children;

}


