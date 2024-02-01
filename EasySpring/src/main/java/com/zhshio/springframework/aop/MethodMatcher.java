package com.zhshio.springframework.aop;/**
 * @Auther: 张帅
 * @Date: 2024/2/1 - 02 - 01 - 11:07
 * @Description: com.zhshio.springframework.aop
 * @version: 1.0
 */

import java.lang.reflect.Method;

/**
 * @description:
 * @author: zs
 * @time: 2024/2/1 11:07
 */

public interface MethodMatcher {
    boolean matches(Method method, Class<?> targetClass);
}
