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
/**
 * 定义作用域注解，用于指示一个类型或方法的实例化策略。
 *
 * 该注解适用于类或方法级别，通过标注@Scope注解，可以指定一个bean是单例（singleton）还是原型（prototype） scope，或者其他自定义的作用域。
 * 默认值为"singleton"，表示被标注的bean是一个单例，在整个应用上下文中只存在一个实例。
 *
 * @Target 标注可以应用于类型（类）或方法上
 * @Retention 标注指示该注解应该在运行时保留
 * @Documented 标注指示该注解应该包含在Javadoc中
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scpoe {

    /**
     * 作用域的值，默认为"singleton"。
     *
     * 可以设置为预定义的作用域名称，如"singleton"或"prototype"，或者其他自定义的作用域名称。
     * 这个值决定了bean的实例化策略。
     *
     * @return 作用域的名称
     */
    String value() default "singleton";

}

