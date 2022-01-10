package com.zhao.www.entity.model.sys;

import com.baomidou.mybatisplus.annotation.*;
import com.zhao.www.annotation.component.AntTabel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author Matthiola incana
 * @create 2021/11/12 11:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_role")
public class SysRole  implements Serializable {

    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;

    @AntTabel(title = "名称")
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

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private String createTime;

    @TableField("deleted")
    private String deleted;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private String updateTime;

    @TableField(value = "update_id", fill = FieldFill.INSERT_UPDATE)
    private String updateId;

    @TableField(exist = false)
    private List<SysMenu> permissions;

}
