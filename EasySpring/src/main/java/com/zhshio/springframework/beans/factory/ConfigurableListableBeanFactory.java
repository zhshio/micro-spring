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
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;

}