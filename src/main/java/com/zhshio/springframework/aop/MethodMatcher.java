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

/**
 * 方法匹配器接口。用于判断指定的方法是否匹配特定的类。
 */
public interface MethodMatcher {
    /**
     * 判断给定的方法是否匹配指定的类。
     *
     * @param method 要判断是否匹配的方法。
     * @param targetClass 要匹配的类。
     * @return 如果方法匹配类，则返回true；否则返回false。
     */
    boolean matches(Method method, Class<?> targetClass);
}

