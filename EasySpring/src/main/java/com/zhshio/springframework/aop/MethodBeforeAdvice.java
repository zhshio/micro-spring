package com.zhshio.springframework.aop;/**
 * @Auther: 张帅
 * @Date: 2024/2/1 - 02 - 01 - 22:30
 * @Description: com.zhshio.springframework.aop
 * @version: 1.0
 */

import java.lang.reflect.Method;

/**
 * @description:
 * @author: zs
 * @time: 2024/2/1 22:30
 */

public interface MethodBeforeAdvice extends BeforeAdvice {
    void before(Method method, Object[] args, Object target) throws Throwable;
}
