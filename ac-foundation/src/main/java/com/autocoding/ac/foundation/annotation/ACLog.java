package com.autocoding.ac.foundation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解类，用于处理访问控制层数据记录
 * Created by JFW on 2017/2/13.
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ACLog {

    /**
     * 日志操作描述
     *
     * @return
     */
    String value() default "";

    String description() default "不可描述";
}
