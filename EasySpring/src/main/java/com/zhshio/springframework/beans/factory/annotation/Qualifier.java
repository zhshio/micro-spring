package com.zhshio.springframework.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * @Auther: 张帅
 * @Date: 2024/2/2 - 02 - 02 - 21:35
 * @Description: com.zhshio.springframework.beans.factory.annotation
 * @version: 1.0
 */

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Qualifier {

    // 注解的值，可以用于进一步指定或区分被注解元素。默认为空字符串。
    String value() default "";
}
