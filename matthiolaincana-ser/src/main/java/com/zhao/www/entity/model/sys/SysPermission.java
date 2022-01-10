package com.zhao.www.entity.model.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author Matthiola incana
 * @create 2021/11/12 11:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_permission")
public class SysPermission implements Serializable {

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
    private String createTime;

    @TableField("update_id")
    private String updateId;

    @TableField("update_time")
    private String updateTime;

    @TableField(exist = false)
    private List<String> actionList;

}
