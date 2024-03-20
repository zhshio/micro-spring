package com.zhshio.springframework.context.annotation;/**
 * @Auther: 张帅
 * @Date: 2024/2/2 - 02 - 02 - 11:32
 * @Description: com.zhshio.springframework.context.annotation
 * @version: 1.0
 */

import java.lang.annotation.*;

/**
 * @description:
 * @author: zs
 * @time: 2024/2/2 11:32
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scpoe {

    String value() default "singleton";

}
