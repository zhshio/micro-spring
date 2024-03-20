package com.zhshio.springframework.context.stereotype;

import java.lang.annotation.*;

/**
 * @Auther: 张帅
 * @Date: 2024/2/2 - 02 - 02 - 11:37
 * @Description: com.zhshio.springframework.context.annotation
 * @version: 1.0
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {
    String value() default "";

}
