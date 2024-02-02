package com.zhshio.springframework.beans.factory.annotation;/**
 * @Auther: 张帅
 * @Date: 2024/2/2 - 02 - 02 - 21:34
 * @Description: com.zhshio.springframework.beans.factory.annotation
 * @version: 1.0
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description:
 * @author: zs
 * @time: 2024/2/2 21:34
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.METHOD})
public @interface Autowired {
}
