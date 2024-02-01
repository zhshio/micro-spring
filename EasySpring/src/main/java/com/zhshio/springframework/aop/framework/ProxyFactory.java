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

public class ProxyFactory {
    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    private AopProxy createAopProxy() {
        if (advisedSupport.isProxyTargetClass()) {
            return new Cglib2AopProxy(advisedSupport);
        }

        return new JdkDynamicAopProxy(advisedSupport);
    }
}
