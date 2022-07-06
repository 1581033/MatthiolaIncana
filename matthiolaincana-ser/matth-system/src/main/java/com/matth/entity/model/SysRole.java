package com.matth.entity.model;

import com.baomidou.mybatisplus.annotation.*;
import com.matth.antui.AntTabel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author Matthiola incana
 * @create 2021/11/12 11:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_role")
@EqualsAndHashCode(callSuper = true)
public class SysRole extends BaseModel {

    @AntTabel(title = "角色编号")
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;

    @AntTabel(title = "角色名称")
    @TableField("name")
    private String name;

    @AntTabel(title = "描述")
    @TableField("role_describe")
    private String roleDescribe;

    @AntTabel(title = "状态")
    @TableField("status")
    private String status;

    @TableField(value = "creator_id", fill = FieldFill.INSERT)
    private String creatorId;

    @AntTabel(title = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(value = "update_id", fill = FieldFill.INSERT_UPDATE)
    private String updateId;

    @TableField(exist = false)
    private List<SysMenu> permissions;

}
