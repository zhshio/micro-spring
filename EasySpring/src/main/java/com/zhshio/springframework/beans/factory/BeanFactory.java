package com.zhshio.springframework.beans.factory;

import com.zhshio.springframework.beans.BeansException;
import com.zhshio.springframework.beans.factory.config.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: 张帅
 * @Date: 2023/11/9 - 11 - 09 - 18:05
 * @Description: com.zhshio.springframework
 * @version: 1.0
 */
public interface BeanFactory {
    Object getBean(String name);

    Object getBean(String name, Object... args) throws BeansException;

    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

    <T> T getBean(Class<T> requiredType) throws BeansException;
}
