package com.zhao.www.entity.model.sys;

import com.baomidou.mybatisplus.annotation.*;
import com.zhao.www.annotation.component.AntTabel;
import com.zhao.www.base.entity.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author Matthiola incana
 * @create 2021/11/10 21:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_user")
@EqualsAndHashCode(callSuper = true)
public class SysUser extends BaseModel {

    @AntTabel(title = "用户昵称")
    @TableField("name")
    private String name;

    @AntTabel(title = "用户名称")
    @TableField("user_name")
    private String username;

    @TableField("pass_word")
    private String password;

    @TableField("avatar")
    private String avatar;

    @AntTabel(title = "状态")
    @TableField("status")
    private String status;

    @AntTabel(title = "手机号码")
    @TableField("telephone")
    private String telephone;

    @TableField("last_login_ip")
    private String lastLoginIp;

    @TableField("last_login_time")
    private String lastLoginTime;

    @AntTabel(title = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(exist = false)
    private List<SysRole> role;

}
