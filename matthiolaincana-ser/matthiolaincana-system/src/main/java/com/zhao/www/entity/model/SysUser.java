package com.zhao.www.entity.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhao.www.antui.AntTabel;
import com.zhao.www.base.entity.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
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
public class SysUser extends BaseModel implements UserDetails {

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

    @TableField(exist = false)
    private Collection<? extends GrantedAuthority> authorities;

    @TableField(exist = false)
    private boolean accountNonExpired = true;

    @TableField("account_non_locked")
    private boolean accountNonLocked;

    @TableField(exist = false)
    private boolean credentialsNonExpired  = true;

    @TableField("enabled")
    private boolean enabled;

    public Authentication getAuthentication(){
        return new UsernamePasswordAuthenticationToken(this,null,authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
