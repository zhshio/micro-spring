package com.zhshio.springframework.beans.factory.support;

import com.zhshio.springframework.beans.BeansException;
import com.zhshio.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @Auther: 张帅
 * @Date: 2023/11/15 - 11 - 15 - 15:23
 * @Description: com.zhshio.springframework.beans.factory.support
 * @version: 1.0
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;

}
