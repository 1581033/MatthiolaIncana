package com.zhao.www.annotation.component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Matthiola incana
 * @create 2021/11/30 15:00
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AntTabel {

    String align() default "";

    boolean ellipsis() default false;

    int colSpan() default 1;

    String dataIndex() default "";

    String title() default "";

    int width() default 200;

    boolean resizable() default true;

}
