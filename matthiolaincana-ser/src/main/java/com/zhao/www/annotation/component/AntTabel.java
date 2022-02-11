package com.zhao.www.annotation.component;

import java.lang.annotation.*;

/**
 * @author Matthiola incana
 * @create 2021/11/30 15:00
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AntTabel {

    String title() default "";

    int sort() default 100;

    String align() default "";

    boolean ellipsis() default false;

    int colSpan() default 1;

    String dataIndex() default "";



    int width() default 200;

    boolean resizable() default true;

}
