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
@TableName(value = "sys_role")
@EqualsAndHashCode(callSuper = true)
public class SysRole extends BaseModel {

    @TableField("name")
    private String name;

    @TableField("code")
    private String code;

    @TableField("role_describe")
    private String roleDescribe;

    @TableField("status")
    private Integer status;

}
