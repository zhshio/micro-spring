package com.zhshio.springframework.aop;/**
 * @Auther: 张帅
 * @Date: 2024/2/1 - 02 - 01 - 11:21
 * @Description: com.zhshio.springframework.aop
 * @version: 1.0
 */

import org.aopalliance.intercept.MethodInterceptor;



/**
 * @description:
 * @author: zs
 * @time: 2024/2/1 11:21
 */

/**
 * 用于配置和管理AOP代理支持的类。
 */
public class AdvisedSupport {

    // 是否使用CGLIB代理目标类，默认为false，表示使用JDK动态代理
    private boolean proxyTargetClass = false;
    // 目标对象的来源，包含实际被代理的对象
    private TargetSource targetSource;
    // 用于拦截目标方法执行的方法拦截器
    private MethodInterceptor methodInterceptor;
    // 用于匹配目标方法是否应该被拦截的方法匹配器
    private MethodMatcher methodMatcher;

    /**
     * 获取是否使用CGLIB代理目标类的配置。
     *
     * @return 如果为true，则使用CGLIB代理；否则，使用JDK动态代理。
     */
    public boolean isProxyTargetClass() {
        return proxyTargetClass;
    }

    /**
     * 设置是否使用CGLIB代理目标类的配置。
     *
     * @param proxyTargetClass 如果为true，则使用CGLIB代理；否则，使用JDK动态代理。
     */
    public void setProxyTargetClass(boolean proxyTargetClass) {
        this.proxyTargetClass = proxyTargetClass;
    }

    /**
     * 获取目标对象的来源。
     *
     * @return 目标对象的来源实例。
     */
    public TargetSource getTargetSource() {
        return targetSource;
    }

    /**
     * 设置目标对象的来源。
     *
     * @param targetSource 目标对象的来源实例。
     */
    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    /**
     * 获取用于拦截目标方法执行的方法拦截器。
     *
     * @return 方法拦截器实例。
     */
    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    /**
     * 设置用于拦截目标方法执行的方法拦截器。
     *
     * @param methodInterceptor 方法拦截器实例。
     */
    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    /**
     * 获取用于匹配目标方法是否应该被拦截的方法匹配器。
     *
     * @return 方法匹配器实例。
     */
    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    /**
     * 设置用于匹配目标方法是否应该被拦截的方法匹配器。
     *
     * @param methodMatcher 方法匹配器实例。
     */
    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}
