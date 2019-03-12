package com.jnshu.dreamteam.config.annotation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 *  自定义参数校验的注解，用于校验手机是否为11位，校验类型为Long型
 * @author wzp
 */
@Target({ElementType.METHOD, ElementType.FIELD}) //注解作用的地方,此处定位为作用与方法和字段上
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneValidation.class)  //指定来处理验证的类
public @interface Phone {

    String message(); //这边可以标注默认的验证失败消息
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

}
