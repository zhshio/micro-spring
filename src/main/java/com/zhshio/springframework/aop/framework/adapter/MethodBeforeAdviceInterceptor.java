package com.zhshio.springframework.aop.framework.adapter;/**
 * @Auther: 张帅
 * @Date: 2024/2/1 - 02 - 01 - 22:43
 * @Description: com.zhshio.springframework.aop.framework.adapter
 * @version: 1.0
 */

import com.zhshio.springframework.aop.MethodBeforeAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @description:
 * @author: zs
 * @time: 2024/2/1 22:43
 */

/**
 * 实现MethodInterceptor接口，用于在方法执行前提供拦截功能的类。
 * 可以通过注入MethodBeforeAdvice实例来定义具体的方法执行前的逻辑。
 */
public class MethodBeforeAdviceInterceptor implements MethodInterceptor {
    private MethodBeforeAdvice advice; // 存储方法执行前的建议对象

    /**
     * 无参构造函数，创建一个不绑定MethodBeforeAdvice的拦截器实例。
     */
    public MethodBeforeAdviceInterceptor() {
    }

    /**
     * 带有MethodBeforeAdvice参数的构造函数，创建一个与具体建议对象绑定的拦截器实例。
     * @param advice 方法执行前的建议对象，用于定义方法执行前的逻辑。
     */
    public MethodBeforeAdviceInterceptor(MethodBeforeAdvice advice) {
        this.advice = advice;
    }

    /**
     * 获取当前拦截器绑定的MethodBeforeAdvice对象。
     * @return 返回当前绑定的方法执行前的建议对象。
     */
    public MethodBeforeAdvice getAdvice() {
        return advice;
    }

    /**
     * 设置当前拦截器要绑定的MethodBeforeAdvice对象。
     * @param advice 要设置的方法执行前的建议对象。
     */
    public void setAdvice(MethodBeforeAdvice advice) {
        this.advice = advice;
    }

    /**
     * 拦截器的核心方法，会在目标方法执行前被调用。
     * @param methodInvocation 方法调用对象，包含调用的方法、参数等信息。
     * @return 返回目标方法的执行结果。
     * @throws Throwable 如果在执行建议逻辑或目标方法时发生异常，则会抛出。
     */
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        // 在目标方法执行前执行advice中的before方法
        this.advice.before(methodInvocation.getMethod(), methodInvocation.getArguments(), methodInvocation.getThis());
        // 继续执行目标方法
        return methodInvocation.proceed();
    }

}
