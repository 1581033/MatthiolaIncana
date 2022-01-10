package com.zhao.www.entity.model.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhao.www.annotation.component.AntTabel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author Matthiola incana
 * @create 2021/11/10 21:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_user")
public class SysUser implements Serializable {

    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;

    @AntTabel(title = "名称")
    @TableField("name")
    private String name;

    @AntTabel(title = "账号")
    @TableField("user_name")
    private String username;

    @TableField("pass_word")
    private String password;

    @AntTabel(title = "头像")
    @TableField("avatar")
    private String avatar;

    @AntTabel(title = "状态")
    @TableField("status")
    private String status;

    @AntTabel(title = "电话")
    @TableField("telephone")
    private String telephone;

    @TableField("last_login_ip")
    private String lastLoginIp;

    @TableField("last_login_time")
    private String lastLoginTime;

    @TableField("deleted")
    private String deleted;

    @TableField("create_time")
    private String createTime;

    @TableField("creator_id")
    private String createId;

    @TableField(exist = false)
    private List<SysRole> role;

}
