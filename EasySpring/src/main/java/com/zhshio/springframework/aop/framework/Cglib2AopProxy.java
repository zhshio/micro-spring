package com.zhshio.springframework.aop.framework;/**
 * @Auther: 张帅
 * @Date: 2024/2/1 - 02 - 01 - 11:40
 * @Description: com.zhshio.springframework.aop.framework
 * @version: 1.0
 */

import com.zhshio.springframework.aop.AdvisedSupport;
import com.zhshio.springframework.utils.ClassUtils;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import java.lang.reflect.Method;

/**
 * @description:
 * @author: zs
 * @time: 2024/2/1 11:40
 */

/**
 * Cglib2AopProxy 类实现了 AopProxy 接口，用于创建 AOP 代理。
 * 它利用 Cglib 库来动态生成目标对象的代理。
 */
public class Cglib2AopProxy implements AopProxy {

    // 存储配置信息，如目标对象、拦截器等
    private final AdvisedSupport advised;

    /**
     * 构造函数
     * @param advised 提供配置信息的对象，包括目标对象、拦截器等
     */
    public Cglib2AopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

    /**
     * 创建并返回一个代理对象。
     * @return 代理对象，该对象是目标对象的 Cglib 动态代理。
     */
    @Override
    public Object getProxy() {
        // 创建一个 Cglib 的增强器，用于配置代理对象
        Enhancer enhancer = new Enhancer();
        // 获取并设置代理对象的父类
        Class<?> aClass = advised.getTargetSource().getTarget().getClass();
        aClass = ClassUtils.isCglibProxyClass(aClass)? aClass.getSuperclass():aClass;
        enhancer.setSuperclass(aClass);
        // 设置代理对象实现的接口
        enhancer.setInterfaces(advised.getTargetSource().getTargetClass());
        // 设置回调函数，即当代理对象的方法被调用时执行的逻辑
        enhancer.setCallback(new DynamicAdvisedInterceptor(advised));
        // 创建并返回代理对象
        return enhancer.create();
    }

    /**
     * 动态拦截器，负责在方法执行前、执行后、发生异常时执行额外的逻辑。
     */
    private static class DynamicAdvisedInterceptor implements MethodInterceptor {

        // 存储配置信息，如目标对象、拦截器等
        private final AdvisedSupport advised;

        /**
         * 构造函数
         * @param advised 提供配置信息的对象，包括目标对象、拦截器等
         */
        public DynamicAdvisedInterceptor(AdvisedSupport advised) {
            this.advised = advised;
        }

        /**
         * 在目标方法执行前，执行拦截逻辑。
         * @param o 代理对象
         * @param method 目标方法
         * @param objects 方法参数
         * @param methodProxy 方法代理
         * @return 方法执行结果
         * @throws Throwable 如果方法执行过程中发生异常
         */
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            // 创建方法调用对象
            CglibMethodInvocation methodInvocation = new CglibMethodInvocation(advised.getTargetSource().getTarget(), method, objects, methodProxy);
            // 判断当前方法是否需要拦截
            if (advised.getMethodMatcher().matches(method, advised.getTargetSource().getTarget().getClass())) {
                // 如果需要拦截，则调用拦截器
                return advised.getMethodInterceptor().invoke(methodInvocation);
            }
            // 如果不需要拦截，则直接执行目标方法
            return methodInvocation.proceed();
        }
    }

    /**
     * Cglib 方法调用对象，继承自 ReflectiveMethodInvocation。
     * 用于封装方法调用的相关信息，提供方法执行的机制。
     */
    private static class CglibMethodInvocation extends ReflectiveMethodInvocation {

        // Cglib 方法代理，用于实际调用目标方法
        private final MethodProxy methodProxy;

        /**
         * 构造函数
         * @param target 目标对象
         * @param method 目标方法
         * @param arguments 方法参数
         * @param methodProxy 方法代理
         */
        public CglibMethodInvocation(Object target, Method method, Object[] arguments, MethodProxy methodProxy) {
            super(target, method, arguments);
            this.methodProxy = methodProxy;
        }

        /**
         * 执行目标方法。
         * @return 方法执行结果
         * @throws Throwable 如果方法执行过程中发生异常
         */
        @Override
        public Object proceed() throws Throwable {
            // 通过 Cglib 方法代理调用目标方法
            return this.methodProxy.invoke(this.target, this.arguments);
        }

    }

}
