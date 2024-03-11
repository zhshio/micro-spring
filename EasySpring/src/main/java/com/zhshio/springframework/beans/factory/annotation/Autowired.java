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

/**
 * Autowired注解用于标注在构造器、字段或方法上，以指示Spring框架在运行时自动注入对应的依赖对象。
 * 该注解的保留策略为运行时，即编译器会保留该注解信息，以便Spring在运行时进行依赖注入。
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.METHOD})
public @interface Autowired {
}

