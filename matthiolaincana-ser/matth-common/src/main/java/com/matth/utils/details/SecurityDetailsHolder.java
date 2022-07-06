package com.matth.utils.details;

import com.matth.entity.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * @author Matthiola incana
 * @create 2022/7/6 11:05
 */
public class SecurityDetailsHolder {

    public static User details() {
        return Optional.ofNullable((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).orElseThrow(NullPointerException::new);
    }

}
