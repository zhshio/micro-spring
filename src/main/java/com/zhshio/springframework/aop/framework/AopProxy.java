package com.zhshio.springframework.aop.framework;

/**
 * @Auther: 张帅
 * @Date: 2024/2/1 - 02 - 01 - 11:37
 * @Description: com.zhshio.springframework.aop.framework
 * @version: 1.0
 */
/**
 * AopProxy 接口定义了创建AOP代理对象的方法。
 */
public interface AopProxy {
    /**
     * 获取AOP代理对象。
     *
     * @return 返回代理对象，该对象在使用AOP时，会根据配置的切面进行拦截处理。
     */
    Object getProxy();
}

