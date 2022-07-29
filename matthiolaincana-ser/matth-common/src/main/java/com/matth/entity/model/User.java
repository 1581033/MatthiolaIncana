package com.matth.entity.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.matth.antui.AntTabel;
import com.matth.entity.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author Matthiola incana
 * @create 2022/7/6 9:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_user")
@EqualsAndHashCode(callSuper = true)
public class User extends BaseModel implements UserDetails {

    @AntTabel(title = "用户昵称")
    @TableField("name")
    private String name;

    @AntTabel(title = "用户名称")
    @TableField("username")
    private String username;

    @TableField("password")
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

    @TableField(exist = false)
    private List<String> roles;

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

    public void setAuthorities(String... list) {
        this.authorities = AuthorityUtils.createAuthorityList(list);
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
