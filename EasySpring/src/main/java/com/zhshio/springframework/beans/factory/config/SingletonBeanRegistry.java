package com.zhshio.springframework.beans.factory.config;

/**
 * @Auther: 张帅
 * @Date: 2023/11/9 - 11 - 09 - 19:55
 * @Description: 获取单例对象的接口
 * @version: 1.0
 */

/**
 * 单例bean注册表接口。
 * 提供了对单例对象的注册和获取操作，用于管理应用中的单例对象。
 */
public interface SingletonBeanRegistry {

    /**
     * 获取指定名称的单例对象。
     *
     * @param beanName 要获取的bean的名称。
     * @return 返回与beanName关联的单例对象，如果没有找到，则返回null。
     */
    Object getSingleton(String beanName);

    /**
     * 注册一个单例对象。
     *
     * @param beanName 要注册的bean的名称。
     * @param singletonObject 要注册的单例对象。
     */
    void registerSingleton(String beanName, Object singletonObject);
}
