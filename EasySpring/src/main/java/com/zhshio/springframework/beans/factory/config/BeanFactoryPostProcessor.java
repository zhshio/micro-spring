package com.zhshio.springframework.beans.factory.config;

import com.zhshio.springframework.beans.BeansException;
import com.zhshio.springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * @Auther: 张帅
 * @Date: 2023/11/18 - 11 - 18 - 23:28
 * @Description: com.zhshio.springframework.beans.factory.config
 * @version: 1.0
 */

public interface BeanFactoryPostProcessor {

    /**
     * @description: 在所有 BeanDefinition 加载完成后, 实例化 Bean对象之前, 提供修改 BeanDefinition属性的机制
     * @author: 张帅
     * @date: 2024/1/3 20:05
     * @param: [beanFactory]
     * @return: [com.zhshio.springframework.beans.factory.ConfigurableListableBeanFactory]
     **/
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}
