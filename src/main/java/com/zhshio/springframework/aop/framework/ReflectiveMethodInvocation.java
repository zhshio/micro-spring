package com.zhshio.springframework.aop.framework;/**
 * @Auther: 张帅
 * @Date: 2024/2/1 - 02 - 01 - 11:45
 * @Description: com.zhshio.springframework.aop.framework
 * @version: 1.0
 */

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * @description:
 * @author: zs
 * @time: 2024/2/1 11:45
 */

/**
 * 使用反射实现的方法调用类，用于动态调用目标对象的方法。
 */
public class ReflectiveMethodInvocation implements MethodInvocation {

    // 目标对象，即要调用方法的对象。
    protected final Object target;
    // 要调用的方法。
    protected final Method method;
    // 方法调用的参数。
    protected final Object[] arguments;

    /**
     * 构造函数，初始化目标对象、方法和参数。
     *
     * @param target 目标对象。
     * @param method 要调用的方法。
     * @param arguments 方法调用的参数数组。
     */
    public ReflectiveMethodInvocation(Object target, Method method, Object[] arguments) {
        this.target = target;
        this.method = method;
        this.arguments = arguments;
    }

    /**
     * 获取要调用的方法。
     *
     * @return 返回 Method 对象，表示要调用的方法。
     */
    @Override
    public Method getMethod() {
        return method;
    }

    /**
     * 获取方法调用的参数数组。
     *
     * @return 返回包含方法参数的 Object 数组。
     */
    @Override
    public Object[] getArguments() {
        return arguments;
    }

    /**
     * 执行方法调用，动态调用目标对象的方法。
     *
     * @return 返回方法的返回值。
     * @throws Throwable 如果方法执行过程中发生异常，则抛出。
     */
    @Override
    public Object proceed() throws Throwable {
        return method.invoke(target, arguments);
    }

    /**
     * 获取目标对象，即要调用方法的对象。
     *
     * @return 返回目标对象。
     */
    @Override
    public Object getThis() {
        return target;
    }

    /**
     * 获取方法调用的静态部分，即方法本身。
     *
     * @return 返回 AccessibleObject，表示可访问的对象（此处为 Method 对象）。
     */
    @Override
    public AccessibleObject getStaticPart() {
        return method;
    }

}
