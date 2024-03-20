package com.zhshio.springframework.aop.framework;/**
 * @Auther: 张帅
 * @Date: 2024/2/1 - 02 - 01 - 11:37
 * @Description: com.zhshio.springframework.aop.framework
 * @version: 1.0
 */

import com.zhshio.springframework.aop.AdvisedSupport;
import org.aopalliance.intercept.MethodInterceptor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * @description:
 * @author: zs
 * @time: 2024/2/1 11:37
 */

/**
 * JDK 动态代理实现的 AOP 代理类。
 * 该类实现了 AopProxy 和 InvocationHandler 两个接口，
 * 用于创建并处理 AOP 代理对象。
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {
    // 存储配置了 AOP 信息的对象
    private final AdvisedSupport advised;

    /**
     * 构造函数，初始化 AOP 相关配置。
     *
     * @param advised 提供了 AOP 代理需要的所有信息的对象，包括目标对象、拦截器等。
     */
    public JdkDynamicAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

    /**
     * 创建并返回一个 AOP 代理对象。
     * 该方法会根据当前线程的上下文类加载器和目标对象的类加载器来创建代理，并将本实例作为 InvocationHandler。
     *
     * @return 返回一个代理对象，该对象实现了目标对象的所有接口。
     */
    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), advised.getTargetSource().getTargetClass(), this);
    }

    /**
     * 当通过代理对象调用方法时，该方法会被调用。
     * 如果方法匹配拦截器的规则，则会调用拦截器，否则直接调用目标方法。
     *
     * @param proxy 代理对象本身。
     * @param method 被调用的方法。
     * @param args 方法调用时传入的参数。
     * @return 返回方法的执行结果。
     * @throws Throwable 如果方法执行过程中发生异常，则抛出。
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 判断方法是否需要拦截
        if (advised.getMethodMatcher().matches(method, advised.getTargetSource().getTarget().getClass())) {
            // 如果需要拦截，则调用拦截器
            MethodInterceptor methodInterceptor = advised.getMethodInterceptor();
            return methodInterceptor.invoke(new ReflectiveMethodInvocation(advised.getTargetSource().getTarget(), method, args));
        }
        // 如果不需要拦截，则直接调用目标方法
        return method.invoke(advised.getTargetSource().getTarget(), args);
    }
}
