package com.zhshio.springframework.beans.factory.support;

import com.zhshio.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: 张帅
 * @Date: 2023/11/9 - 11 - 09 - 19:58
 * @Description: 单例模板方法抽象类
 * @version: 1.0
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String, Object> singletonObjects = new HashMap<>();
    @Override
    public Object getSingleton(String beanName) {

        return singletonObjects.get(beanName);
    }

    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }
}
