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
/**
 * 实例化策略接口。定义了根据Bean定义来实例化对象的方法。
 */
public interface InstantiationStrategy {

    /**
     * 使用指定的构造函数和参数实例化一个Bean。
     *
     * @param beanDefinition 描述Bean的定义，包含了Bean的配置信息。
     * @param beanName Bean的名称。
     * @param ctor 要使用的构造函数。
     * @param args 传递给构造函数的参数。
     * @return 实例化的Bean对象。
     * @throws BeansException 如果实例化过程中发生错误。
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;

}

