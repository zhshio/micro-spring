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
/**
 * BeanFactory接口定义了获取Bean实例的方法集合。
 * 它是Spring框架中最重要的接口之一，提供了检索和访问应用上下文中Bean实例的功能。
 */
public interface BeanFactory {

    /**
     * 根据Bean的名称获取Bean实例。
     *
     * @param name 要获取的Bean的名称。
     * @return 返回与给定名称匹配的Bean实例。如果找不到对应的Bean，抛出NoSuchBeanDefinitionException异常。
     */
    Object getBean(String name);

    /**
     * 根据Bean的名称和参数获取Bean实例。可以用于触发Bean的构造函数并传递参数。
     *
     * @param name 要获取的Bean的名称。
     * @param args 传递给Bean构造函数的参数。
     * @return 返回与给定名称匹配的Bean实例。如果找不到对应的Bean，抛出NoSuchBeanDefinitionException异常。
     * @throws BeansException 如果获取Bean时发生错误。
     */
    Object getBean(String name, Object... args) throws BeansException;

    /**
     * 根据Bean的名称和期望类型获取Bean实例。
     *
     * @param name 要获取的Bean的名称。
     * @param requiredType 期望获取的Bean的类型。
     * @return 返回与给定名称和类型匹配的Bean实例。如果找不到对应的Bean，抛出NoSuchBeanDefinitionException异常。
     * @throws BeansException 如果获取Bean时发生错误。
     * @throws ClassCastException 如果返回的Bean实例不是期望的类型。
     */
    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

    /**
     * 根据期望的类型获取Bean实例。会返回与给定类型匹配的任意一个Bean实例。
     *
     * @param requiredType 期望获取的Bean的类型。
     * @return 返回与给定类型匹配的Bean实例。如果找不到任何匹配的Bean，抛出NoSuchBeanDefinitionException异常。
     * @throws BeansException 如果获取Bean时发生错误。
     * @throws ClassCastException 如果返回的Bean实例不是期望的类型。
     */
    <T> T getBean(Class<T> requiredType) throws BeansException;

    /**
     * 检查BeanFactory中是否包含指定名称的Bean。
     *
     * @param name 要检查的Bean的名称。
     * @return 如果BeanFactory包含指定名称的Bean，则返回true；否则返回false。
     */
    boolean containsBean(String name);
}

