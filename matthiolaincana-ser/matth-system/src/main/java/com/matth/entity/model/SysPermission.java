package com.matth.entity.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author Matthiola incana
 * @create 2021/11/12 11:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_permission")
@EqualsAndHashCode(callSuper = true)
public class SysPermission extends BaseModel {

    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;

    @TableField("menu_id")
    private String menuId;

    @TableField("action")
    private String action;

    @TableField("describe")
    private String describe;

    @TableField("default_check")
    private String defaultCheck;

    @TableField("creator_id")
    private String creatorId;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_id")
    private String updateId;

    @TableField("update_time")
    private Date updateTime;

    @TableField(exist = false)
    private List<String> actionList;

}
