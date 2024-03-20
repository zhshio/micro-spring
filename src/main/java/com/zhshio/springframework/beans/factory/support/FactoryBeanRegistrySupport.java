package com.zhshio.springframework.beans.factory.support;/**
 * @Auther: 张帅
 * @Date: 2024/1/31 - 01 - 31 - 9:26
 * @Description: com.zhshio.springframework.beans.factory.support
 * @version: 1.0
 */

import com.zhshio.springframework.beans.BeansException;
import com.zhshio.springframework.beans.factory.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: zs
 * @time: 2024/1/31 9:26
 */

/**
 * 支持工厂Bean注册的抽象类，扩展自DefaultSingletonBeanRegistry。
 */
public abstract class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry{
    // 工厂Bean对象的缓存，用于存储工厂Bean创建的对象
    private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>();

    /**
     * 获取工厂Bean缓存中的对象。
     *
     * @param beanName Bean的名称
     * @return 缓存中对应工厂Bean的对象。如果不存在或为NULL_OBJECT，则返回null。
     */
    protected Object getCachedObjectForFactoryBean(String beanName) {
        Object object = this.factoryBeanObjectCache.get(beanName);
        return (object != NULL_OBJECT ? object : null);
    }

    /**
     * 从工厂Bean获取对象。
     *
     * @param factory 工厂Bean实例
     * @param beanName Bean的名称
     * @return 工厂Bean创建的对象。如果工厂Bean配置为单例且对象已缓存，则返回缓存的对象；如果不是单例，每次调用都会创建新对象。
     */
    protected Object getObjectFromFactoryBean(FactoryBean factory, String beanName) {
        if (factory.isSingleton()) { // 如果工厂Bean是单例
            Object objcet = this.factoryBeanObjectCache.get(beanName);
            if (objcet == null) { // 缓存中不存在，则创建对象并加入缓存
                objcet = doGetObjectFromFactoryBean(factory, beanName);
                this.factoryBeanObjectCache.put(beanName, (null != objcet ? objcet : NULL_OBJECT));
            }

            return (NULL_OBJECT != objcet ? objcet : null);
        } else { // 如果工厂Bean不是单例，直接创建对象
            return doGetObjectFromFactoryBean(factory, beanName);
        }

    }

    /**
     * 从给定的工厂Bean获取对象。
     *
     * @param factory 工厂Bean实例
     * @param beanName Bean的名称
     * @return 工厂Bean创建的对象
     * @throws BeansException 如果工厂Bean在创建对象时抛出异常
     */
    private Object doGetObjectFromFactoryBean(final FactoryBean factory, final String beanName){
        try {
            return factory.getObject();
        } catch (Exception e) {
            throw new BeansException("FactoryBean threw exception on object[" + beanName + "] creation", e);
        }
    }
}
