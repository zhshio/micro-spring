package com.zhshio.springframework.beans.factory;

import com.zhshio.springframework.beans.BeansException;
import com.zhshio.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.zhshio.springframework.beans.factory.config.BeanDefinition;
import com.zhshio.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * @Auther: 张帅
 * @Date: 2023/11/18 - 11 - 18 - 17:18
 * @Description: com.zhshio.springframework.beans.factory
 * @version: 1.0
 */
/**
 * 提供一种配置化的、可列举的bean工厂接口，扩展了ListableBeanFactory, AutowireCapableBeanFactory和ConfigurableBeanFactory接口。
 * 支持获取bean定义、预实例化单例等功能。
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    /**
     * 根据bean名称获取bean的定义信息。
     *
     * @param beanName 要获取定义的bean的名称。
     * @return 对应bean名称的BeanDefinition对象。
     * @throws BeansException 如果获取过程中发生错误。
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 预实例化所有的单例bean。这会在工厂被初始化后、实际使用前调用，以确保所有单例bean都被实例化。
     *
     * @throws BeansException 如果实例化过程中发生错误。
     */
    void preInstantiateSingletons() throws BeansException;

}
