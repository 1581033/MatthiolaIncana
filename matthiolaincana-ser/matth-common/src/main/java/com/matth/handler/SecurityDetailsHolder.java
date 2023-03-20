package com.matth.handler;

import com.matth.entity.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Matthiola incana
 * @create 2022/6/17 12:00
 */
public class SecurityDetailsHolder {

    public static User details() {
        return Optional.ofNullable((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).orElseThrow(NullPointerException::new);
    }

    public static List<String> roles() {
        return Arrays.stream(SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()).map(Object::toString).collect(Collectors.toList());
    }

}
