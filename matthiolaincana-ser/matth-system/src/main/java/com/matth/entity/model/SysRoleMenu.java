package com.matth.entity.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Matthiola incana
 * @create 2021/12/17 10:13
 */
@Data
@TableName(value = "sys_role_menu")
@EqualsAndHashCode(callSuper = true)
public class SysRoleMenu extends BaseModel {

    @TableField("role_id")
    private String roleId;

    @TableField("menu_id")
    private String menuId;

}
