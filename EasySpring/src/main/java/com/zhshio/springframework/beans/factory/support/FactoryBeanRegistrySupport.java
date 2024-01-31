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

public abstract class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry{
    private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>();

    protected Object getCachedObjectForFactoryBean(String beanName) {
        Object object = this.factoryBeanObjectCache.get(beanName);
        return (object != NULL_OBJECT ? object : null);
    }

    protected Object getObjectFromFactoryBean(FactoryBean factory, String beanName) {
        if (factory.isSingleton()) {
            Object objcet = this.factoryBeanObjectCache.get(beanName);
            if (objcet == null) {
                objcet = doGetObjectFromFactoryBean(factory, beanName);
                this.factoryBeanObjectCache.put(beanName, (null != objcet ? objcet : NULL_OBJECT));
            }

            return (NULL_OBJECT != objcet ? objcet : null);
        } else {
            return doGetObjectFromFactoryBean(factory, beanName);
        }

    }

    private Object doGetObjectFromFactoryBean(final FactoryBean factory, final String beanName){
        try {
            return factory.getObject();
        } catch (Exception e) {
            throw new BeansException("FactoryBean threw exception on object[" + beanName + "] creation", e);
        }
    }
}
