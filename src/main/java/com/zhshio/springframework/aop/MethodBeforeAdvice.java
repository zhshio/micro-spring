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

/**
 * 方法执行前的建议接口，继承自BeforeAdvice接口。提供了一个在方法执行前执行自定义逻辑的机会。
 *
 * @author 光明日报
 * @see BeforeAdvice
 */
public interface MethodBeforeAdvice extends BeforeAdvice {
    /**
     * 在目标方法执行前执行的方法。
     *
     * @param method 将要执行的方法对象。
     * @param args 将要传递给方法的参数数组。
     * @param target 方法所属的对象实例。
     * @throws Throwable 如果在执行自定义逻辑时发生异常，可以抛出Throwable。
     */
    void before(Method method, Object[] args, Object target) throws Throwable;
}
