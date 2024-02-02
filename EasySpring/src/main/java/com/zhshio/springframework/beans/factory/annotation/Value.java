package com.zhshio.springframework.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * @Auther: 张帅
 * @Date: 2024/2/2 - 02 - 02 - 21:36
 * @Description: com.zhshio.springframework.beans.factory.annotation
 * @version: 1.0
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Value {
    String value();

}
