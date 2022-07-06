package com.zhao.www.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Matthiola incana
 * @create 2021/10/14 15:46
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserAuthenticate {
    /**
     * @return 是否开启验证处理
     */
    boolean permission() default true;
}
