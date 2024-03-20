package com.zhshio.springframework.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * @Auther: 张帅
 * @Date: 2024/2/2 - 02 - 02 - 21:36
 * @Description: com.zhshio.springframework.beans.factory.annotation
 * @version: 1.0
 */
/**
 * Value注解定义了一个通用的注解，可用于字段、方法和参数上。
 * 它的目的是为了标注一个具体的值，提供一种从元数据中读取配置值或其他信息的方式。
*/

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Value {

    // value()方法用于指定注解的具体值。
    String value();

}

