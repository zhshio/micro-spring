package com.zhshio.springframework.context.stereotype;

import java.lang.annotation.*;

/**
 * @Auther: 张帅
 * @Date: 2024/2/2 - 02 - 02 - 11:37
 * @Description: com.zhshio.springframework.context.annotation
 * @version: 1.0
 */

/**
 * 标记一个类作为组件的注解。
 *
 * 该注解用于标注一个类作为Spring框架中的组件，使其可以被Spring容器管理。
 * 被此注解标注的类可以被自动扫描并加入到Spring的应用上下文中。
 * 默认情况下，没有指定任何特定的值，但如果需要，可以通过value属性指定一个唯一的标识符。
 *
 * @Target(ElementType.TYPE) 指定该注解可以应用于类上。
 * @Retention(RetentionPolicy.RUNTIME) 指定该注解在运行时可见，可以被反射机制读取。
 * @Documented 包含该注解的文档将被包含在生成的API文档中。
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {
    String value() default "";

}
