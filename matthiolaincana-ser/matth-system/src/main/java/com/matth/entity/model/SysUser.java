package com.matth.entity.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fhs.core.trans.anno.Trans;
import com.fhs.core.trans.constant.TransType;
import com.fhs.core.trans.vo.TransPojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author Matthiola incana
 * @create 2021/11/10 21:16
 */
@Data
@TableName(value = "sys_user")
@EqualsAndHashCode(callSuper = true)
public class SysUser extends BaseModel implements TransPojo {

    @TableField("name")
    private String name;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("avatar")
    private String avatar;

    @TableField("status")
    private Integer status;

    @TableField("telephone")
    private String telephone;

    @TableField("last_login_ip")
    private String lastLoginIp;

    @TableField("last_login_time")
    private Date lastLoginTime;

    @TableField("account_non_locked")
    private boolean accountNonLocked;

    @TableField("enabled")
    private boolean enabled;

    @TableField
    @Trans(type = TransType.SIMPLE,target = SysDepartment.class,fields = {"name"},alias = "dept")
    private String deptId;

}
