package com.jnshu.dreamteam.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用AOP的方式记录日志
 * 请求源IP，请求的方法名，,执行结果，执行时间
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogInfo {

    String value() default "";

}
