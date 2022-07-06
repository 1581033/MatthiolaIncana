package com.zhao.www.handler;

import com.zhao.www.entity.model.SysUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

/**
 * @author Matthiola incana
 * @create 2022/6/17 12:00
 */
public class SecurityDetailsHolder {

    public static UserDetails details() {
        return Optional.ofNullable((SysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).orElseThrow(NullPointerException::new);
    }

}
