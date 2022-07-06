package com.zhao.www.entity.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhao.www.base.entity.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Matthiola incana
 * @create 2021/12/17 10:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_role_menu")
@EqualsAndHashCode(callSuper = true)
public class SysRoleMenu extends BaseModel {

    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;

    @TableField("role_id")
    private String roleId;

    @TableField("menu_id")
    private String menuId;

}
