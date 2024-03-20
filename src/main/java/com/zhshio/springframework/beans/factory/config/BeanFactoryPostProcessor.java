package com.zhshio.springframework.beans.factory.config;

import com.zhshio.springframework.beans.BeansException;
import com.zhshio.springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * @Auther: 张帅
 * @Date: 2023/11/18 - 11 - 18 - 23:28
 * @Description: com.zhshio.springframework.beans.factory.config
 * @version: 1.0
 */

/**
 * BeanFactory后处理器接口。
 * 提供在所有BeanDefinition加载完成后，但在实例化Bean对象之前，修改BeanDefinition属性的功能。
 */
public interface BeanFactoryPostProcessor {

    /**
     * 对BeanFactory进行后处理，允许修改BeanDefinition的属性。
     *
     * @param beanFactory 可配置的列表Bean工厂，提供对BeanDefinition的访问和修改能力。
     * @throws BeansException 如果处理过程中发生错误。
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}

