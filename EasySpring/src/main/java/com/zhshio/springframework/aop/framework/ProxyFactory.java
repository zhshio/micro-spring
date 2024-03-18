package com.zhshio.springframework.aop.framework;/**
 * @Auther: 张帅
 * @Date: 2024/2/1 - 02 - 01 - 23:06
 * @Description: com.zhshio.springframework.aop.framework
 * @version: 1.0
 */

import com.zhshio.springframework.aop.AdvisedSupport;

/**
 * @description:
 * @author: zs
 * @time: 2024/2/1 23:06
 */

/**
 * 代理工厂类，用于根据配置信息创建AOP代理对象。
 */
public class ProxyFactory {
    // 存储配置了AOP代理信息的对象
    private AdvisedSupport advisedSupport;

    /**
     * 构造函数，初始化代理工厂。
     *
     * @param advisedSupport 配置了AOP代理信息的对象，包含目标对象、拦截器等信息。
     */
    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    /**
     * 创建并返回一个AOP代理对象。
     *
     * @return 返回一个AOP代理对象，该对象封装了目标对象和拦截器链。
     */
    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    /**
     * 根据配置决定使用哪种方式创建AOP代理。
     *
     * @return 返回一个AopProxy实例，根据配置可能是Cglib代理或JDK动态代理。
     */
    private AopProxy createAopProxy() {
        // 检查是否需要使用目标类的代理
        if (advisedSupport.isProxyTargetClass()) {
            // 如果需要，创建并返回Cglib代理
            return new Cglib2AopProxy(advisedSupport);
        }

        // 否则，创建并返回JDK动态代理
        return new JdkDynamicAopProxy(advisedSupport);
    }
}
