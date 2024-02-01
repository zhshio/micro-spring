package com.zhshio.springframework.aop;

/**
 * @Auther: 张帅
 * @Date: 2024/2/1 - 02 - 01 - 11:07
 * @Description: com.zhshio.springframework.aop
 * @version: 1.0
 */
public interface ClassFilter {
    boolean matches(Class<?> clazz);
}
