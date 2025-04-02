package com.tan.annotation.anno;

import java.lang.annotation.*;

/**
 * 接口 API 的注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Inherited
public @interface BankAPI {
    String desc() default "";
    String url() default "";
}