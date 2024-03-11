package com.zhshio.springframework.beans.factory.support;

import com.zhshio.springframework.beans.factory.config.BeanDefinition;

/**
 * @Auther: 张帅
 * @Date: 2023/11/9 - 11 - 09 - 20:06
 * @Description: com.zhshio.springframework.beans.factory.support
 * @version: 1.0
 */
/**
 * Bean定义注册表接口，用于注册和查询Bean定义。
 */
public interface BeanDefinitionRegistry {

    /**
     * 注册一个Bean定义。
     *
     * @param beanName 要注册的Bean的名称，必须是唯一的。
     * @param beanDefinition 要注册的Bean的定义，描述了Bean的创建方式和属性。
     * @throws IllegalStateException 如果已经存在同名的Bean定义，则抛出异常。
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 检查是否包含指定名称的Bean定义。
     *
     * @param beanName 要查询的Bean的名称。
     * @return 如果找到同名的Bean定义，则返回true；否则返回false。
     */
    boolean containsBeanDefinition(String beanName);
}

