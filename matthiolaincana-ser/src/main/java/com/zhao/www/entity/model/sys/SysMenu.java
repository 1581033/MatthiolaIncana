package com.zhao.www.entity.model.sys;

import com.baomidou.mybatisplus.annotation.*;
import com.zhao.www.annotation.component.AntTabel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Matthiola incana
 * @create 2021/11/10 20:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_menu")
public class SysMenu implements Serializable {

    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;

    @AntTabel(title = "路径")
    @TableField("path")
    private String path;

    @AntTabel(title = "名称")
    @TableField("name")
    private String name;

    @AntTabel(title = "组件")
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

    @AntTabel(title = "标题")
    @TableField("title")
    private String title;

    @AntTabel(title = "图标")
    @TableField("icon")
    private String icon;

    @TableField("keep_alive")
    private String keepAlive;

    @TableField("hidden_header_content")
    private String hiddenHeaderContent;

    @TableField(value = "creator_id", fill = FieldFill.INSERT)
    private String creatorId;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_id", fill = FieldFill.INSERT_UPDATE)
    private String updateId;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField("permission")
    private String permission;

    @TableField(exist = false)
    private List<SysPermission> actionEntitySet;

    @TableField(exist = false)
    private List<SysMenu> children = new ArrayList<>();

}


